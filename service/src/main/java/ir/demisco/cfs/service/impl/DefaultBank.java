package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.BankSaveRequuest;
import ir.demisco.cfs.model.dto.response.BankListResponse;
import ir.demisco.cfs.model.entity.Bank;
import ir.demisco.cfs.service.api.BankService;
import ir.demisco.cfs.service.repository.BankRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DefaultBank implements BankService {
    private final GridFilterService gridFilterService;
    private final BankListProvider bankListProvider;
    private final BankRepository bankRepository;

    public DefaultBank(GridFilterService gridFilterService, BankListProvider bankListProvider, BankRepository bankRepository) {
        this.gridFilterService = gridFilterService;
        this.bankListProvider = bankListProvider;
        this.bankRepository = bankRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getListBank(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        DataSourceResult dataSourceResult = gridFilterService.filter(dataSourceRequest, bankListProvider);
        List<BankListResponse> data = (List<BankListResponse>) dataSourceResult.getData();
        List<BankListResponse> bankListResponses = new ArrayList<>();
        for (BankListResponse bankListResponse : data) {
            if (bankListResponse.getDisableDate() == null) bankListResponse.setActiveFlag(1L);
            bankListResponses.add(bankListResponse);
        }
        dataSourceResult.setData(bankListResponses);
        return dataSourceResult;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean getFinancialAccountByIdAndStatusFlag(BankSaveRequuest bankSaveRequuest) {

        Bank bank = bankRepository.findById(bankSaveRequuest.getBankId() == null ? 0 : bankSaveRequuest.getBankId()).orElse(new Bank());
        if (bankSaveRequuest.getBankId() != null) {
            if (bankSaveRequuest.getActiveFlag() == 0) {
                bank.setDisableDate(new Date());
            } else {
                bank.setDisableDate(null);
            }
        }
        Long financialAccountStructureCount = bankRepository.getCountByBankAndCodeAndDeletedDate(bankSaveRequuest.getBankCode());
        if (financialAccountStructureCount > 0) {
            throw new RuleException("بانکی با این اطلاعات قبلا ثبت شده است.");
        }
        bank.setCode(bankSaveRequuest.getBankCode());
        bank.setName(bankSaveRequuest.getBankName());
        bank.setFormatNumber(bankSaveRequuest.getFormatNumber());
        bank.setIconName(bankSaveRequuest.getIconName());
        bankRepository.save(bank);
        return true;
    }
}

