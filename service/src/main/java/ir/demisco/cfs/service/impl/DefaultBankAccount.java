package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.BankAccountChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.BankAccountSaveRequest;
import ir.demisco.cfs.model.dto.response.BankAccountListResponse;
import ir.demisco.cfs.model.entity.BankAccount;
import ir.demisco.cfs.service.api.BankAccountService;
import ir.demisco.cfs.service.repository.BankAccountDepartmentRepository;
import ir.demisco.cfs.service.repository.BankAccountRepository;
import ir.demisco.cfs.service.repository.BankAccountTypeRepository;
import ir.demisco.cfs.service.repository.BankBranchRepository;
import ir.demisco.cfs.service.repository.BankRepository;
import ir.demisco.cfs.service.repository.CentricAccountRepository;
import ir.demisco.cfs.service.repository.FinancialAccountRepository;
import ir.demisco.cfs.service.repository.MoneyTypeRepository;
import ir.demisco.cfs.service.repository.OrganizationRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DefaultBankAccount implements BankAccountService {
    private final GridFilterService gridFilterService;
    private final BankAccountListProvider bankAccountListProvider;
    private final BankAccountRepository bankAccountRepository;
    private final MoneyTypeRepository moneyTypeRepository;
    private final BankAccountTypeRepository bankAccountTypeRepository;
    private final BankRepository bankRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final BankBranchRepository bankBranchRepository;
    private final CentricAccountRepository centricAccountRepository;
    private final OrganizationRepository organizationRepository;
    private final BankAccountDepartmentRepository bankAccountDepartmentRepository;

    public DefaultBankAccount(GridFilterService gridFilterService, BankAccountListProvider bankAccountListProvider, BankAccountRepository bankAccountRepository, MoneyTypeRepository moneyTypeRepository, BankAccountTypeRepository bankAccountTypeRepository, BankRepository bankRepository, FinancialAccountRepository financialAccountRepository, BankBranchRepository bankBranchRepository, CentricAccountRepository centricAccountRepository, OrganizationRepository organizationRepository, BankAccountDepartmentRepository bankAccountDepartmentRepository) {
        this.gridFilterService = gridFilterService;
        this.bankAccountListProvider = bankAccountListProvider;
        this.bankAccountRepository = bankAccountRepository;
        this.moneyTypeRepository = moneyTypeRepository;
        this.bankAccountTypeRepository = bankAccountTypeRepository;
        this.bankRepository = bankRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.bankBranchRepository = bankBranchRepository;
        this.centricAccountRepository = centricAccountRepository;
        this.organizationRepository = organizationRepository;
        this.bankAccountDepartmentRepository = bankAccountDepartmentRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getListBankAccount(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("bank.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("bankBranch.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("financialAccount.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("bankAccountType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("moneyType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        DataSourceResult dataSourceResult = gridFilterService.filter(dataSourceRequest, bankAccountListProvider);
        List<BankAccountListResponse> data = (List<BankAccountListResponse>) dataSourceResult.getData();
        List<BankAccountListResponse> bankAccountListResponses = new ArrayList<>();
        for (BankAccountListResponse bankAccountListResponse : data) {
            if (bankAccountListResponse.getDisableDate() == null) {
                bankAccountListResponse.setActiveFlag(true);
            }
            bankAccountListResponses.add(bankAccountListResponse);
        }
        dataSourceResult.setData(bankAccountListResponses);
        return dataSourceResult;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)

    public Boolean saveBankAccount(BankAccountSaveRequest bankAccountSaveRequest) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountSaveRequest.getBankAccountId() == null ? 0 : bankAccountSaveRequest.getBankAccountId()).orElse(new BankAccount());
        Long bankAccountCount;
        Long bankAccountCodeShebaCount;
        bankAccountCount = bankAccountRepository.getCountByBankAccountByCodeAndBankAccountTypeAndBank(bankAccountSaveRequest.getBankAccountCode(), bankAccountSaveRequest.getBankId(), bankAccountSaveRequest.getBankAccountTypeId());
        bankAccountCodeShebaCount = bankAccountRepository.getCountByBankAccountByAccountCodeSheba(bankAccountSaveRequest.getAccountCodeSheba());
        if (bankAccountSaveRequest.getBankAccountId() == null && bankAccountCount > 0 && bankAccountCodeShebaCount > 0) {
            throw new RuleException("fin.bank.uniqueBankAccount");
        }
//        if (bankAccountCodeShebaCount > 0) {
//            throw new RuleException("fin.bank.uniqueBankAccountCodeSheba");
//        }
        if (bankAccountSaveRequest.getBankAccountId() != null) {
            if (Boolean.TRUE.equals(!bankAccountSaveRequest.getActiveFlag())) {
                bankAccount.setDisableDate(new Date());
            } else {
                bankAccount.setDisableDate(null);
            }
        } else {
            bankAccount.setDisableDate(null);
        }
        bankAccount.setCode(bankAccountSaveRequest.getBankAccountCode());
        bankAccount.setDescription(bankAccountSaveRequest.getDescription());
        bankAccount.setMoneyType(bankAccountSaveRequest.getMoneyTypeId() != null ?
                moneyTypeRepository.getOne(bankAccountSaveRequest.getMoneyTypeId()) : null);
        bankAccount.setBankAccountType(bankAccountTypeRepository.getOne(bankAccountSaveRequest.getBankAccountTypeId()));
        bankAccount.setAccountOwnerName(bankAccountSaveRequest.getAccountOwnerName());
        bankAccount.setAccountCodeSheba(bankAccountSaveRequest.getAccountCodeSheba());
        bankAccount.setBank(bankRepository.getOne(bankAccountSaveRequest.getBankId()));
        bankAccount.setFinancialAccount(bankAccountSaveRequest.getFinancialAccountId() != null ?
                financialAccountRepository.getOne(bankAccountSaveRequest.getFinancialAccountId()) : null);
        bankAccount.setBankBranch(bankBranchRepository.getOne(bankAccountSaveRequest.getBankBranchId()));
        bankAccount.setSupportAccount(bankAccountSaveRequest.getSupportAccountId() != null ?
                bankAccountRepository.getOne(bankAccountSaveRequest.getSupportAccountId()) : null);
        bankAccount.setCentricAccount(bankAccountSaveRequest.getCentricAccountId() != null ?
                centricAccountRepository.getOne(bankAccountSaveRequest.getCentricAccountId()) : null);
        Long organizationId = SecurityHelper.getCurrentUser().getOrganizationId();
        bankAccount.setOrganization(organizationRepository.getOne(organizationId));

        bankAccount.setBankAccountDepartment(bankAccountSaveRequest.getBankAccountDepartmentId() != null ?
                bankAccountDepartmentRepository.getOne(bankAccountSaveRequest.getBankAccountDepartmentId()) : null);
        bankAccount.setInternetFlag(bankAccountSaveRequest.getInternetFlag());
        bankAccount.setRelatedFlag(bankAccountSaveRequest.getRelatedFlag());
        bankAccount.setDefaultFlag(bankAccountSaveRequest.getDefaultFlag());
        bankAccountRepository.save(bankAccount);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean getBankAccountChangeStatus(BankAccountChangeStatusRequest bankAccountChangeStatusRequest) {
        if (bankAccountChangeStatusRequest.getBankAccountId() == null || bankAccountChangeStatusRequest.getActiveFlag() == null) {
            throw new RuleException("fin.bankAccount.changeStatus");
        }
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountChangeStatusRequest.getBankAccountId() == null ? 0 : bankAccountChangeStatusRequest.getBankAccountId()).orElse(new BankAccount());
        if (Boolean.TRUE.equals(!bankAccountChangeStatusRequest.getActiveFlag())) {
            bankAccount.setDisableDate(new Date());
        } else {
            bankAccount.setDisableDate(null);
        }
        return true;
    }
}



