package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.BankBranchGetRequest;
import ir.demisco.cfs.model.dto.request.BankBranchRequest;
import ir.demisco.cfs.model.dto.response.BankBranchGetResponse;
import ir.demisco.cfs.model.dto.response.BankBranchListResponse;
import ir.demisco.cfs.model.entity.Bank;
import ir.demisco.cfs.model.entity.BankAccount;
import ir.demisco.cfs.model.entity.BankBranch;
import ir.demisco.cfs.model.entity.CentricPersonRole;
import ir.demisco.cfs.service.api.BankBranchService;
import ir.demisco.cfs.service.repository.BankAccountRepository;
import ir.demisco.cfs.service.repository.BankBranchRepository;
import ir.demisco.cfs.service.repository.BankRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultBankBranch implements BankBranchService {
    private final GridFilterService gridFilterService;
    private final BankBranchListProvider bankBranchListProvider;
    private BankBranchRepository bankBranchRepository;
    private BankRepository bankRepository;
    private BankAccountRepository bankAccountRepository;

    public DefaultBankBranch(GridFilterService gridFilterService, BankBranchListProvider bankBranchListProvider, BankBranchRepository bankBranchRepository, BankRepository bankRepository, BankAccountRepository bankAccountRepository) {
        this.gridFilterService = gridFilterService;
        this.bankBranchListProvider = bankBranchListProvider;
        this.bankBranchRepository = bankBranchRepository;
        this.bankRepository = bankRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getListBankBranch(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        DataSourceResult dataSourceResult = gridFilterService.filter(dataSourceRequest, bankBranchListProvider);
        List<BankBranchListResponse> data = (List<BankBranchListResponse>) dataSourceResult.getData();
        List<BankBranchListResponse> bankBranchListResponses = new ArrayList<>();
        for (BankBranchListResponse bankBranchListResponse : data) {
            if (bankBranchListResponse.getDisableDate() == null) bankBranchListResponse.setActiveFlag(true);
            bankBranchListResponses.add(bankBranchListResponse);
        }
        dataSourceResult.setData(bankBranchListResponses);
        return dataSourceResult;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<BankBranchGetResponse> getBankBranch(BankBranchGetRequest bankBranchGetRequest) {
        List<Object[]> bankBranchListObject = bankBranchRepository.findByBankBranchByBankId(bankBranchGetRequest.getBankId());

        return bankBranchListObject.stream().map(objects -> BankBranchGetResponse.builder().bankId(Long.parseLong(objects[1].toString()))
                .branchId(Long.parseLong(objects[0].toString()))
                .branchCode(objects[3].toString())
                .branchName(objects[2].toString())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean saveBankBranch(BankBranchRequest bankBranchRequest) {
        BankBranch bankBranch = bankBranchRepository.findById(bankBranchRequest.getBranchId() == null ? 0 : bankBranchRequest.getBranchId()).orElse(new BankBranch());
        Long bankBranchCount;
        if (bankBranchRequest.getBankId() == null) {
            throw new RuleException("لطفا شناسه ی بانک را وارد نمایید.");

        }
        if (bankBranchRequest.getBranchCode() == null) {
            throw new RuleException("لطفا کد شعبه را وارد نمایید.");
        }


        if (bankBranchRequest.getBranchName() == null) {
            throw new RuleException("لطفا نام شعبه را وارد نمایید.");
        }
//        if (bankBranchRequest.getBranchId() != null) {
//            if (bankBranchRequest.getActiveFlag().equals(false)) {
//                bankBranch.setDisableDate(new Date());
//            } else {
//                bankBranch.setDisableDate(null);
//            }
//        }

        if (bankBranchRequest.getBranchId() == null) {
            bankBranchCount = bankBranchRepository.getCountByBankBranchAndCodeAndDeletedDateAndBank(bankBranchRequest.getBranchCode(), bankBranchRequest.getBankId());
            if (bankBranchCount > 0) {
                throw new RuleException("شعبه ای با این اطلاعات قبلا ثبت شده است.");
            }
        } else {
            bankBranchCount = bankBranchRepository.getCountBankBranchByCodeAndBankIdAndDeletedDate(bankBranchRequest.getBranchCode(), bankBranchRequest.getBranchId(), bankBranchRequest.getBankId());
            if (bankBranchCount > 0) {
                throw new RuleException("شعبه ای با این اطلاعات قبلا ثبت شده است.");
            }
            if (!bankBranchRequest.getActiveFlag()) {
                bankBranch.setDisableDate(new Date());
            } else {
                bankBranch.setDisableDate(null);
            }
        }
        bankBranch.setBank(bankRepository.getOne(bankBranchRequest.getBankId()));
        bankBranch.setCode(bankBranchRequest.getBranchCode());
        bankBranch.setName(bankBranchRequest.getBranchName());
        bankBranch.setTelNumber(bankBranchRequest.getTelNumber());
        bankBranch.setFaxNumber(bankBranchRequest.getFaxNumber());
        bankBranch.setBoxCode(bankBranchRequest.getBoxCode());
        bankBranch.setPostCode(bankBranchRequest.getPostCode());
        bankBranch.setAddress(bankBranchRequest.getBranchAddress());
        bankBranchRepository.save(bankBranch);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteBankBranch(Long bankBranchId) {
        List<BankAccount> bankAccounts = bankAccountRepository.findByBankBranchId(bankBranchId);
        BankBranch bankBranch;
        if (!bankAccounts.isEmpty()) {
            throw new RuleException("fin.bankBranch.delete");
        } else {
            bankBranch = bankBranchRepository.findById(bankBranchId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
            bankBranch.setDeletedDate(LocalDateTime.now());
            bankBranchRepository.save(bankBranch);
            return true;
        }

    }

}

