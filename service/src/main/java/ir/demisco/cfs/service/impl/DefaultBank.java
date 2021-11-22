package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.BankListResponse;
import ir.demisco.cfs.service.api.BankService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBank implements BankService {
    private final GridFilterService gridFilterService;
    private final BankListProvider bankListProvider;

    public DefaultBank(GridFilterService gridFilterService, BankListProvider bankListProvider) {
        this.gridFilterService = gridFilterService;
        this.bankListProvider = bankListProvider;
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
}
