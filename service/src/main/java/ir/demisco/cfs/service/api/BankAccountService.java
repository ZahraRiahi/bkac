package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.BankAccountChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.BankAccountSaveRequest;
import ir.demisco.cfs.model.dto.request.BankBranchChangeStatusRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

public interface BankAccountService {
    DataSourceResult getListBankAccount(DataSourceRequest dataSourceRequest);

    Boolean saveBankAccount(BankAccountSaveRequest bankAccountSaveRequest);

    Boolean getBankAccountChangeStatus(BankAccountChangeStatusRequest bankAccountChangeStatusRequest);
}