package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.BankSaveRequest;
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
            if (bankListResponse.getDisableDate() == null) bankListResponse.setActiveFlag(true);
            bankListResponses.add(bankListResponse);
        }
        dataSourceResult.setData(bankListResponses);
        return dataSourceResult;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean saveBank(BankSaveRequest bankSaveRequest) {

        Bank bank = bankRepository.findById(bankSaveRequest.getBankId() == null ? 0 : bankSaveRequest.getBankId()).orElse(new Bank());
        Long bankCount;
        if (bankSaveRequest.getBankId() != null) {
            if (bankSaveRequest.getActiveFlag().equals(true)) {
                bank.setDisableDate(new Date());
            } else {
                bank.setDisableDate(null);
            }
        }

        if (bank.getId() == null) {
            bankCount = bankRepository.getCountByBankAndCodeAndDeletedDate(bankSaveRequest.getBankCode());
            if (bankCount > 0) {
                throw new RuleException("fin.bank.uniqueBank");
            }
        } else {
            bankCount = bankRepository.getCountBankByCodeAndIdAndDeletedDate(bankSaveRequest.getBankCode(), bankSaveRequest.getBankId());
            if (bankCount > 0) {
                throw new RuleException("fin.bank.uniqueBank");
            }
            if (!bankSaveRequest.getActiveFlag()) {
                bank.setDisableDate(new Date());
            } else {
                bank.setDisableDate(null);
            }
        }
        bank.setCode(bankSaveRequest.getBankCode());
        bank.setName(bankSaveRequest.getBankName());
        bank.setFormatNumber(bankSaveRequest.getFormatNumber());
        bank.setIconName(bankSaveRequest.getIconName());
        bankRepository.save(bank);
        return true;
    }
}

