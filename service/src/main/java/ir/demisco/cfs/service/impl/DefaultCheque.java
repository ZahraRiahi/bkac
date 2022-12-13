package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.ChequeChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.ChequeStatusListRequest;
import ir.demisco.cfs.model.entity.Cheque;
import ir.demisco.cfs.service.api.ChequeService;
import ir.demisco.cfs.service.repository.ChequeBookRepository;
import ir.demisco.cfs.service.repository.ChequeRepository;
import ir.demisco.cfs.service.repository.ChequeStatusRepository;
import ir.demisco.cfs.service.repository.ChequeUseTypeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DefaultCheque implements ChequeService {
    private final GridFilterService gridFilterService;
    private final ChequeListProvider chequeListProvider;
    private final ChequeRepository chequeRepository;
    private final ChequeStatusRepository chequeStatusRepository;
    private final ChequeUseTypeRepository chequeUseTypeRepository;
    private final ChequeBookRepository chequeBookRepository;

    public DefaultCheque(GridFilterService gridFilterService, ChequeListProvider chequeListProvider, ChequeRepository chequeRepository, ChequeStatusRepository chequeStatusRepository, ChequeUseTypeRepository chequeUseTypeRepository, ChequeBookRepository chequeBookRepository) {
        this.gridFilterService = gridFilterService;
        this.chequeListProvider = chequeListProvider;
        this.chequeRepository = chequeRepository;
        this.chequeStatusRepository = chequeStatusRepository;
        this.chequeUseTypeRepository = chequeUseTypeRepository;
        this.chequeBookRepository = chequeBookRepository;
    }


    @Override
    @Transactional
    public DataSourceResult getListCheque(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("chequeUseType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        return gridFilterService.filter(dataSourceRequest, chequeListProvider);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean updateChequeChangeStatus(ChequeChangeStatusRequest chequeChangeStatusRequest) {
        Long oldChequeStatusId;
        oldChequeStatusId = chequeRepository.getChequeStatusByChequeId(chequeChangeStatusRequest.getChequeId());

        if (chequeChangeStatusRequest.getChequeId() == 2 && oldChequeStatusId != 1) {
            throw new RuleException("fin.chequeStatus.equalTwoAndNotEqualOne");
        }
        if (chequeChangeStatusRequest.getChequeId() == 3 && oldChequeStatusId != 2) {
            throw new RuleException("fin.chequeStatus.equalThreeAndNotEqualTwo");
        }
        update(chequeChangeStatusRequest);
        if (chequeChangeStatusRequest.getChequeId() == 7 && (oldChequeStatusId != 1 || oldChequeStatusId != 5)) {
            throw new RuleException("fin.chequeStatus.equalSevenAndNotEqualOneOrFive");
        } else {
            Cheque cheque = chequeRepository.findById(chequeChangeStatusRequest.getChequeId() == null ? 0 : chequeChangeStatusRequest.getChequeId()).orElse(new Cheque());
            if (cheque.getDeletedDate() == null) {
                cheque.setChequeStatus(chequeChangeStatusRequest.getChequeStatusId() != null ?
                        chequeStatusRepository.getOne(chequeChangeStatusRequest.getChequeStatusId()) : null);
                chequeRepository.save(cheque);
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean saveCheque(ChequeStatusListRequest chequeStatusListRequest) {
        Cheque cheque = chequeRepository.findById(chequeStatusListRequest.getId() == null ? 0 : chequeStatusListRequest.getId()).orElse(new Cheque());
        List<Long> chequeUnique = chequeRepository.findByChequeBookAndChequeNumberAndChequeBookId(chequeStatusListRequest.getChequeNumber(), chequeStatusListRequest.getChequeBookId());
        if (chequeUnique.size() > 1) {
            throw new RuleException("fin.chequeBook.useCheque");
        }
        if (chequeStatusListRequest.getId() != null && chequeStatusListRequest.getChequeStatusId() != 1) {
            throw new RuleException("در این وضعیت امکان ویرایش اطلاعات وجود ندارد");
        }
        if (chequeStatusListRequest.getDescription() == null) {
            throw new RuleException("fin.chequeBookType.description");
        }
        if (chequeStatusListRequest.getFlagRemit() == null) {
            throw new RuleException("fin.chequeBookType.flagRemit");
        }
        if (chequeStatusListRequest.getChequeUseTypeId() == null) {
            throw new RuleException("fin.chequeBookType.chequeUseType");
        }
        if (chequeStatusListRequest.getChequeBookId() == null) {
            throw new RuleException("fin.chequeBookType.chequeBook");
        }
        if (chequeStatusListRequest.getChequeStatusId() == null) {
            throw new RuleException("fin.chequeBookType.chequeStatus");
        }
        if (chequeStatusListRequest.getChequeNumber() == null) {
            throw new RuleException("fin.chequeBookType.chequeNumber");
        }
        cheque.setChequeNumber(chequeStatusListRequest.getChequeNumber());
        cheque.setUseDate(chequeStatusListRequest.getUseDate());
        cheque.setChequeUseType(chequeUseTypeRepository.getOne(chequeStatusListRequest.getChequeUseTypeId()));
        cheque.setNationalCode(chequeStatusListRequest.getNationalCode());
        cheque.setDescription(chequeStatusListRequest.getDescription());
        cheque.setAmount(chequeStatusListRequest.getAmount());
        cheque.setFlagRemit(chequeStatusListRequest.getFlagRemit());
        cheque.setChequeBook(chequeBookRepository.getOne(chequeStatusListRequest.getChequeBookId()));
        cheque.setChequeStatus(chequeStatusRepository.getOne(chequeStatusListRequest.getChequeStatusId()));
        chequeRepository.save(cheque);
        return true;
    }

    public Boolean update(ChequeChangeStatusRequest chequeChangeStatusRequest) {
        Long oldChequeStatusId;
        oldChequeStatusId = chequeRepository.getChequeStatusByChequeId(chequeChangeStatusRequest.getChequeId());
        if (chequeChangeStatusRequest.getChequeId() == 4 && oldChequeStatusId != 3) {
            throw new RuleException("fin.chequeStatus.equalFourAndNotEqualThree");
        }
        if (chequeChangeStatusRequest.getChequeId() == 5 && (oldChequeStatusId != 3 || oldChequeStatusId != 4)) {
            throw new RuleException("fin.chequeStatus.equalFiveAndNotEqualThreeOrFour");
        }
        if (chequeChangeStatusRequest.getChequeId() == 6 && oldChequeStatusId != 3) {
            throw new RuleException("fin.chequeStatus.equalSixAndNotEqualThree");
        }

        return false;
    }
}