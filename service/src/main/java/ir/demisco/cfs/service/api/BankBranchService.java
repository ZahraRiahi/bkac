package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.BankBranchGetRequest;
import ir.demisco.cfs.model.dto.request.BankBranchRequest;
import ir.demisco.cfs.model.dto.response.BankBranchGetResponse;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

import java.util.List;

public interface BankBranchService {
    DataSourceResult getListBankBranch(DataSourceRequest dataSourceRequest);

    List<BankBranchGetResponse> getBankBranch(BankBranchGetRequest bankBranchGetRequest);

    Boolean saveBankBranch(BankBranchRequest bankBranchRequest);

    Boolean deleteBankBranch(Long bankBranchId);
}
