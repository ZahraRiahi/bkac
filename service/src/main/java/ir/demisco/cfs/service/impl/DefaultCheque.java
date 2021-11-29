package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.service.api.ChequeService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class DefaultCheque implements ChequeService {
    private final GridFilterService gridFilterService;
    private final ChequeListProvider chequeListProvider;

    public DefaultCheque(GridFilterService gridFilterService, ChequeListProvider chequeListProvider) {
        this.gridFilterService = gridFilterService;
        this.chequeListProvider = chequeListProvider;
    }


    @Override
    @Transactional
    public DataSourceResult getListCheque(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("chequeUseType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        return gridFilterService.filter(dataSourceRequest, chequeListProvider);
    }
}
