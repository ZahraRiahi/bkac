package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.BankAccountListResponse;
import ir.demisco.cfs.service.api.BankAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBankAccount implements BankAccountService {
    private final GridFilterService gridFilterService;
    private final BankAccountListProvider bankAccountListProvider;

    public DefaultBankAccount(GridFilterService gridFilterService, BankAccountListProvider bankAccountListProvider) {
        this.gridFilterService = gridFilterService;
        this.bankAccountListProvider = bankAccountListProvider;
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
            if (bankAccountListResponse.getDisableDate() == null) bankAccountListResponse.setActiveFlag(true);
            bankAccountListResponses.add(bankAccountListResponse);
        }
        dataSourceResult.setData(bankAccountListResponses);
        return dataSourceResult;
    }
}



