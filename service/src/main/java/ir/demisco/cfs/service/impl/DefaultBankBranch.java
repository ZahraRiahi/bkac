package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.BankBranchListResponse;
import ir.demisco.cfs.service.api.BankBranchService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBankBranch implements BankBranchService {
    private final GridFilterService gridFilterService;
    private final BankBranchListProvider bankBranchListProvider;

    public DefaultBankBranch(GridFilterService gridFilterService, BankBranchListProvider bankBranchListProvider) {
        this.gridFilterService = gridFilterService;
        this.bankBranchListProvider = bankBranchListProvider;
    }

    @Override
    @Transactional
    public DataSourceResult getListBankBranch(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        DataSourceResult dataSourceResult = gridFilterService.filter(dataSourceRequest, bankBranchListProvider);
        List<BankBranchListResponse> data = (List<BankBranchListResponse>) dataSourceResult.getData();
        List<BankBranchListResponse> bankBranchListResponses = new ArrayList<>();
        for (BankBranchListResponse bankBranchListResponse : data) {
            if (bankBranchListResponse.getDisableDate() == null) bankBranchListResponse.setActiveFlag(1L);
            bankBranchListResponses.add(bankBranchListResponse);
        }
        dataSourceResult.setData(bankBranchListResponses);
        return dataSourceResult;
    }
}

