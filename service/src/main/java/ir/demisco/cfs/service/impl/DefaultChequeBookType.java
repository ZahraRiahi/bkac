package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.ChequeBookTypeRequest;
import ir.demisco.cfs.model.dto.response.ChequeBookTypeListResponse;
import ir.demisco.cfs.model.entity.ChequeBook;
import ir.demisco.cfs.model.entity.ChequeBookType;
import ir.demisco.cfs.service.api.ChequeBookTypeService;
import ir.demisco.cfs.service.repository.BankRepository;
import ir.demisco.cfs.service.repository.ChequeBookRepository;
import ir.demisco.cfs.service.repository.ChequeBookTypeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DefaultChequeBookType implements ChequeBookTypeService {
    private final GridFilterService gridFilterService;
    private final ChequeBookTypeListProvider chequeBookTypeListProvider;
    private final ChequeBookTypeRepository chequeBookTypeRepository;
    private final BankRepository bankRepository;
    private final ChequeBookRepository chequeBookRepository;

    public DefaultChequeBookType(GridFilterService gridFilterService, ChequeBookTypeListProvider chequeBookTypeListProvider, ChequeBookTypeRepository chequeBookTypeRepository, BankRepository bankRepository, ChequeBookRepository chequeBookRepository) {
        this.gridFilterService = gridFilterService;
        this.chequeBookTypeListProvider = chequeBookTypeListProvider;
        this.chequeBookTypeRepository = chequeBookTypeRepository;
        this.bankRepository = bankRepository;
        this.chequeBookRepository = chequeBookRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getListChequeBookType(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        DataSourceResult dataSourceResult = gridFilterService.filter(dataSourceRequest, chequeBookTypeListProvider);
        List<ChequeBookTypeListResponse> data = (List<ChequeBookTypeListResponse>) dataSourceResult.getData();
        List<ChequeBookTypeListResponse> chequeBookTypeListResponses = new ArrayList<>();
        for (ChequeBookTypeListResponse chequeBookTypeListResponse : data) {
            if (chequeBookTypeListResponse.getDisableDate() == null) {
                chequeBookTypeListResponse.setActiveFlag(true);
            }
            chequeBookTypeListResponses.add(chequeBookTypeListResponse);
        }
        dataSourceResult.setData(chequeBookTypeListResponses);
        return dataSourceResult;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean saveChequeBookType(ChequeBookTypeRequest chequeBookTypeRequest) {
        ChequeBookType chequeBookType = chequeBookTypeRepository.findById(chequeBookTypeRequest.getChequeBookTypeId() == null ? 0 : chequeBookTypeRequest.getChequeBookTypeId()).orElse(new ChequeBookType());
        if (chequeBookTypeRequest.getDescription() == null) {
            throw new RuleException("fin.chequeBookType.description");
        }
        if (chequeBookTypeRequest.getFlagRemit() == null) {
            throw new RuleException("fin.chequeBookType.flagRemit");
        }
        if (chequeBookTypeRequest.getChequeCount() == null) {
            throw new RuleException("fin.chequeBookType.chequeCount");
        }
        if (chequeBookTypeRequest.getChequeBookTypeId() != null) {
            if (Boolean.TRUE.equals(!chequeBookTypeRequest.getActiveFlag())) {
                chequeBookType.setDisableDate(new Date());
            } else {
                chequeBookType.setDisableDate(null);
            }
        }
        chequeBookType.setBank(chequeBookTypeRequest.getBankId() != null ?
                bankRepository.getOne(chequeBookTypeRequest.getBankId()) : null);
        chequeBookType.setDescription(chequeBookTypeRequest.getDescription());
        chequeBookType.setFlagRemit(chequeBookTypeRequest.getFlagRemit());
        chequeBookType.setSubTitle(chequeBookTypeRequest.getSubTitle());
        chequeBookType.setChequeCount(chequeBookTypeRequest.getChequeCount());
        chequeBookTypeRepository.save(chequeBookType);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteChequeBookType(Long chequeBookTypeId) {
        List<ChequeBook> chequeBooks = chequeBookRepository.findByChequeBookTypeId(chequeBookTypeId);
        ChequeBookType chequeBookType;
        if (!chequeBooks.isEmpty()) {
            throw new RuleException("fin.chequeBookType.delete");
        } else {
            chequeBookType = chequeBookTypeRepository.findById(chequeBookTypeId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
            Long countByIdForDelete = chequeBookTypeRepository.getCountByIdForDelete(chequeBookTypeId);
            if (countByIdForDelete > 0) {
                throw new RuleException("fin.chequeBookType.delete");
            } else {
                chequeBookTypeRepository.deleteById(chequeBookType.getId());
                return true;
            }
        }
    }
}

