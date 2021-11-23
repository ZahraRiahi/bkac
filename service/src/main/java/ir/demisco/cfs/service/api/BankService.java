package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.BankSaveRequuest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

public interface BankService {
    DataSourceResult getListBank(DataSourceRequest dataSourceRequest);

    Boolean getFinancialAccountByIdAndStatusFlag(BankSaveRequuest bankSaveRequuest);

}
