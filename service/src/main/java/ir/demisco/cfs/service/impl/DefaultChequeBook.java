package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.ChequeBookListResponse;
import ir.demisco.cfs.service.api.ChequeBookService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultChequeBook implements ChequeBookService {
    private final GridFilterService gridFilterService;
    private final ChequeBookProvider chequeBookProvider;

    public DefaultChequeBook(GridFilterService gridFilterService, ChequeBookProvider chequeBookProvider) {
        this.gridFilterService = gridFilterService;
        this.chequeBookProvider = chequeBookProvider;
    }

    @Override
    @Transactional
    public DataSourceResult getListChequeBook(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("chequeBookType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        DataSourceResult dataSourceResult = gridFilterService.filter(dataSourceRequest, chequeBookProvider);
        List<ChequeBookListResponse> data = (List<ChequeBookListResponse>) dataSourceResult.getData();
        List<ChequeBookListResponse> chequeBookListResponses = new ArrayList<>();
        for (ChequeBookListResponse chequeBookListResponse : data) {
            if (chequeBookListResponse.getDisableDate() == null) chequeBookListResponse.setActiveFlag(true);
            chequeBookListResponses.add(chequeBookListResponse);
        }
        dataSourceResult.setData(chequeBookListResponses);
        return dataSourceResult;
    }

}
