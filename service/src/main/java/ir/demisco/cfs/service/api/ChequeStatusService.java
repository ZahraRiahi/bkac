package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.ChequeStatusListInputRequest;
import ir.demisco.cfs.model.dto.response.ChequeUseTypeListOutputResponse;

import java.util.List;

public interface ChequeStatusService {
    List<ChequeUseTypeListOutputResponse> getChequeStatus(ChequeStatusListInputRequest chequeStatusListInputRequest);

}
