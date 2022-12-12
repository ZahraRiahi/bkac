package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.ChequeUseTypeListInputRequest;
import ir.demisco.cfs.model.dto.response.ChequeUseTypeListOutputResponse;
import ir.demisco.cfs.service.api.ChequeUseTypeService;
import ir.demisco.cfs.service.repository.ChequeUseTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultChequeUseType implements ChequeUseTypeService {
    private final ChequeUseTypeRepository chequeUseTypeRepository;

    public DefaultChequeUseType(ChequeUseTypeRepository chequeUseTypeRepository) {
        this.chequeUseTypeRepository = chequeUseTypeRepository;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<ChequeUseTypeListOutputResponse> getChequeUseType(ChequeUseTypeListInputRequest chequeUseTypeListInputRequest) {
        Object chequeUseTypeCodeObject = null;
        if (chequeUseTypeListInputRequest.getChequeUseTypeCode() != null) {
            chequeUseTypeCodeObject = "chequeUseTypeCodeObject";
        } else {
            chequeUseTypeListInputRequest.setChequeUseTypeCode(null);
        }
        Object chequeUseType = null;
        if (chequeUseTypeListInputRequest.getChequeUseTypeId() != null) {
            chequeUseType = "chequeUseType";
        } else {
            chequeUseTypeListInputRequest.setChequeUseTypeId(0L);
        }
        List<Object[]> chequeUseTypeObject = chequeUseTypeRepository.findByChequeUseTypeByCodeAndId(chequeUseTypeCodeObject,
                chequeUseTypeListInputRequest.getChequeUseTypeCode(), chequeUseType, chequeUseTypeListInputRequest.getChequeUseTypeId());
        return chequeUseTypeObject.stream().map(objects -> ChequeUseTypeListOutputResponse.builder().id(Long.parseLong(objects[0].toString()))
                .code(objects[1].toString())
                .description(objects[2].toString())
                .build()).collect(Collectors.toList());
    }
}
