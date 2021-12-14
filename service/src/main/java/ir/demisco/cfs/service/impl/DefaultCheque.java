package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.ChequeChangeStatusRequest;
import ir.demisco.cfs.model.entity.Cheque;
import ir.demisco.cfs.service.api.ChequeService;
import ir.demisco.cfs.service.repository.ChequeRepository;
import ir.demisco.cfs.service.repository.ChequeStatusRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultCheque implements ChequeService {
    private final GridFilterService gridFilterService;
    private final ChequeListProvider chequeListProvider;
    private final ChequeRepository chequeRepository;
    private final ChequeStatusRepository chequeStatusRepository;

    public DefaultCheque(GridFilterService gridFilterService, ChequeListProvider chequeListProvider, ChequeRepository chequeRepository, ChequeStatusRepository chequeStatusRepository) {
        this.gridFilterService = gridFilterService;
        this.chequeListProvider = chequeListProvider;
        this.chequeRepository = chequeRepository;
        this.chequeStatusRepository = chequeStatusRepository;
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
        } else if (chequeChangeStatusRequest.getChequeId() == 3 && oldChequeStatusId != 2) {
            throw new RuleException("fin.chequeStatus.equalThreeAndNotEqualTwo");
        } else if (chequeChangeStatusRequest.getChequeId() == 4 && oldChequeStatusId != 3) {
            throw new RuleException("fin.chequeStatus.equalFourAndNotEqualThree");
        } else if (chequeChangeStatusRequest.getChequeId() == 5 && (oldChequeStatusId != 3 || oldChequeStatusId != 4)) {
            throw new RuleException("fin.chequeStatus.equalFiveAndNotEqualThreeOrFour");
        } else if (chequeChangeStatusRequest.getChequeId() == 6 && oldChequeStatusId != 3) {
            throw new RuleException("fin.chequeStatus.equalSixAndNotEqualThree");
        } else if (chequeChangeStatusRequest.getChequeId() == 7 && (oldChequeStatusId != 1 || oldChequeStatusId != 5)) {
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

}
