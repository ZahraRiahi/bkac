package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.ChequeStatusListInputRequest;
import ir.demisco.cfs.model.dto.response.ChequeUseTypeListOutputResponse;
import ir.demisco.cfs.service.api.ChequeStatusService;
import ir.demisco.cfs.service.repository.ChequeStatusRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultChequeStatus implements ChequeStatusService {
    private final ChequeStatusRepository chequeStatusRepository;

    public DefaultChequeStatus(ChequeStatusRepository chequeStatusRepository) {
        this.chequeStatusRepository = chequeStatusRepository;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<ChequeUseTypeListOutputResponse> getChequeStatus(ChequeStatusListInputRequest chequeStatusListInputRequest) {
        Object chequeStatusObject = null;
        if (chequeStatusListInputRequest.getChequeStatusCode() != null) {
            chequeStatusObject = "chequeStatusObject";
        } else {
            chequeStatusListInputRequest.setChequeStatusCode(null);
        }
        Object chequeStatus = null;
        if (chequeStatusListInputRequest.getChequeStatusId() != null) {
            chequeStatus = "chequeStatus";
        } else {
            chequeStatusListInputRequest.setChequeStatusId(0L);
        }
        List<Object[]> chequeUseTypeObject = chequeStatusRepository.findByChequeStatusByCodeAndId(chequeStatusObject,
                chequeStatusListInputRequest.getChequeStatusCode(), chequeStatus, chequeStatusListInputRequest.getChequeStatusId());
        return chequeUseTypeObject.stream().map(objects -> ChequeUseTypeListOutputResponse.builder().id(Long.parseLong(objects[0].toString()))
                .code(objects[1].toString())
                .description(objects[2].toString())
                .build()).collect(Collectors.toList());
    }
}

