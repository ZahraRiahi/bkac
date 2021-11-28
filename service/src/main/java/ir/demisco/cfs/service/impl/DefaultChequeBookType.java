package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.ChequeBookTypeListResponse;
import ir.demisco.cfs.service.api.ChequeBookTypeService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class DefaultChequeBookType implements ChequeBookTypeService {
    private final GridFilterService gridFilterService;
    private final ChequeBookTypeListProvider chequeBookTypeListProvider;

    public DefaultChequeBookType(GridFilterService gridFilterService, ChequeBookTypeListProvider chequeBookTypeListProvider) {
        this.gridFilterService = gridFilterService;
        this.chequeBookTypeListProvider = chequeBookTypeListProvider;
    }

    @Override
    @Transactional
    public DataSourceResult getListChequeBookType(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        DataSourceResult dataSourceResult = gridFilterService.filter(dataSourceRequest, chequeBookTypeListProvider);
        List<ChequeBookTypeListResponse> data = (List<ChequeBookTypeListResponse>) dataSourceResult.getData();
        List<ChequeBookTypeListResponse> chequeBookTypeListResponses = new ArrayList<>();
        for (ChequeBookTypeListResponse chequeBookTypeListResponse : data) {
            if (chequeBookTypeListResponse.getDisableDate() == null) chequeBookTypeListResponse.setActiveFlag(true);
            chequeBookTypeListResponses.add(chequeBookTypeListResponse);
        }
        dataSourceResult.setData(chequeBookTypeListResponses);
        return dataSourceResult;
    }
}
