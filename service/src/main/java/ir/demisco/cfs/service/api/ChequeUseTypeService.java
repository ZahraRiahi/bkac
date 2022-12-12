package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.ChequeUseTypeListInputRequest;
import ir.demisco.cfs.model.dto.response.ChequeUseTypeListOutputResponse;

import java.util.List;

public interface ChequeUseTypeService {
    List<ChequeUseTypeListOutputResponse> getChequeUseType(ChequeUseTypeListInputRequest chequeUseTypeListInputRequest);
}
