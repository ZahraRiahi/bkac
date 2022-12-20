package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.ChequeBookChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.ChequeBookFilterModelRequest;
import ir.demisco.cfs.model.dto.request.ChequeBookRequest;
import ir.demisco.cfs.model.dto.response.ChequeBookOutputModelResponse;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.validation.Errors;

import java.util.List;

public interface ChequeBookService {
    DataSourceResult getListChequeBook(DataSourceRequest dataSourceRequest);

    Boolean deleteChequeBook(Long chequeBookId);

    Boolean getChequeBookChangeStatus(ChequeBookChangeStatusRequest chequeBookChangeStatusRequest);

    Boolean saveChequeBook(ChequeBookRequest chequeBookRequest, Errors errors);

    List<ChequeBookOutputModelResponse> getChequeBookList(ChequeBookFilterModelRequest chequeBookFilterModelRequest);


}
