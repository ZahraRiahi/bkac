package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.ChequeBookListResponse;
import ir.demisco.cfs.model.entity.Cheque;
import ir.demisco.cfs.model.entity.ChequeBook;
import ir.demisco.cfs.service.api.ChequeBookService;
import ir.demisco.cfs.service.repository.ChequeBookRepository;
import ir.demisco.cfs.service.repository.ChequeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultChequeBook implements ChequeBookService {
    private final GridFilterService gridFilterService;
    private final ChequeBookProvider chequeBookProvider;
    private final ChequeRepository chequeRepository;
    private final ChequeBookRepository chequeBookRepository;

    public DefaultChequeBook(GridFilterService gridFilterService, ChequeBookProvider chequeBookProvider, ChequeRepository chequeRepository, ChequeBookRepository chequeBookRepository) {
        this.gridFilterService = gridFilterService;
        this.chequeBookProvider = chequeBookProvider;
        this.chequeRepository = chequeRepository;
        this.chequeBookRepository = chequeBookRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getListChequeBook(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("chequeBookType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        DataSourceResult dataSourceResult = gridFilterService.filter(dataSourceRequest, chequeBookProvider);
        List<ChequeBookListResponse> data = (List<ChequeBookListResponse>) dataSourceResult.getData();
        List<ChequeBookListResponse> chequeBookListResponses = new ArrayList<>();
        for (ChequeBookListResponse chequeBookListResponse : data) {
            if (chequeBookListResponse.getDisableDate() == null) chequeBookListResponse.setActiveFlag(true);
            chequeBookListResponses.add(chequeBookListResponse);
        }
        dataSourceResult.setData(chequeBookListResponses);
        return dataSourceResult;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteChequeBook(Long chequeBookId) {
        Long cheques = chequeRepository.findByChequeAndChequeBookIdAndChequeStatusId(chequeBookId);
        ChequeBook chequeBook;
        if (cheques != null) {
            throw new RuleException("برگه های این دسته چک استفاده شده و نمیتوان حذف کرد");
        } else {
            chequeBook = chequeBookRepository.findById(chequeBookId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
            if (chequeBook.getDeletedDate() == null) {
                chequeBook.setDeletedDate(LocalDateTime.now());
                List<Cheque> chequeList = chequeRepository.findByChequeBookId(chequeBookId);
                chequeList.forEach(e -> e.setDeletedDate(LocalDateTime.now()));
            } else {
                throw new RuleException("fin.chequeBook.deleteIsNull");
            }
            chequeBookRepository.save(chequeBook);
            return true;
        }

    }

}
