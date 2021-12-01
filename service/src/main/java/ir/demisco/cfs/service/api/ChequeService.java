package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.ChequeChangeStatusRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

public interface ChequeService {
    DataSourceResult getListCheque(DataSourceRequest dataSourceRequest);

    Boolean updateChequeChangeStatus(ChequeChangeStatusRequest chequeChangeStatusRequest);

}
