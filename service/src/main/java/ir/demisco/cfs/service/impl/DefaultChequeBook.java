package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.ChequeBookChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.ChequeBookRequest;
import ir.demisco.cfs.model.dto.response.ChequeBookListResponse;
import ir.demisco.cfs.model.entity.Cheque;
import ir.demisco.cfs.model.entity.ChequeBook;
import ir.demisco.cfs.service.api.ChequeBookService;
import ir.demisco.cfs.service.repository.*;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DefaultChequeBook implements ChequeBookService {
    private final GridFilterService gridFilterService;
    private final ChequeBookProvider chequeBookProvider;
    private final ChequeRepository chequeRepository;
    private final ChequeBookRepository chequeBookRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ChequeBookTypeRepository chequeBookTypeRepository;
    private final ChequeUseTypeRepository chequeUseTypeRepository;
    private final ChequeStatusRepository chequeStatusRepository;

    public DefaultChequeBook(GridFilterService gridFilterService, ChequeBookProvider chequeBookProvider, ChequeRepository chequeRepository, ChequeBookRepository chequeBookRepository, BankAccountRepository bankAccountRepository, ChequeBookTypeRepository chequeBookTypeRepository, ChequeUseTypeRepository chequeUseTypeRepository, ChequeStatusRepository chequeStatusRepository) {
        this.gridFilterService = gridFilterService;
        this.chequeBookProvider = chequeBookProvider;
        this.chequeRepository = chequeRepository;
        this.chequeBookRepository = chequeBookRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.chequeBookTypeRepository = chequeBookTypeRepository;
        this.chequeUseTypeRepository = chequeUseTypeRepository;
        this.chequeStatusRepository = chequeStatusRepository;
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

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean getChequeBookChangeStatus(ChequeBookChangeStatusRequest chequeBookChangeStatusRequest) {
        if (chequeBookChangeStatusRequest.getChequeBookId() == null || chequeBookChangeStatusRequest.getActiveFlag() == null) {
            throw new RuleException("fin.chequeBook.changeStatus");
        }
        ChequeBook chequeBook = chequeBookRepository.findById(chequeBookChangeStatusRequest.getChequeBookId() == null ? 0 : chequeBookChangeStatusRequest.getChequeBookId()).orElse(new ChequeBook());
        List<Cheque> chequeList = chequeRepository.findByChequeBookId(chequeBookChangeStatusRequest.getChequeBookId());
        if (!chequeBookChangeStatusRequest.getActiveFlag()) {
            chequeBook.setDisableDate(new Date());
            chequeList.forEach(e -> e.setDisableDate(new Date()));
        } else {
            chequeBook.setDisableDate(null);
            chequeList.forEach(e -> e.setDisableDate(null));
        }

        return true;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean saveChequeBook(ChequeBookRequest chequeBookRequest, Errors errors) {
        ChequeBook chequeBook = chequeBookRepository.findById(chequeBookRequest.getChequeBookId() == null ? 0 : chequeBookRequest.getChequeBookId()).orElse(new ChequeBook());
        if (errors.hasErrors()) {
            throw new RuleException(errors.getAllErrors().get(0).getDefaultMessage());
        }

        Long chequeBooks = chequeBookRepository.findByChequeBookAndBankAccountAndNumStart(chequeBookRequest.getBankAccountId(), chequeBookRequest.getNumStart());
        if (chequeBooks >= 1) {
            throw new RuleException("این دسته چک قبلا ثبت شده است. ");
        }
        chequeBook.setBankAccount(chequeBookRequest.getBankAccountId() != null ?
                bankAccountRepository.getOne(chequeBookRequest.getBankAccountId()) : null);
        chequeBook.setChequeBookType(chequeBookRequest.getChequeBookTypeId() != null ?
                chequeBookTypeRepository.getOne(chequeBookRequest.getChequeBookTypeId()) : null);
        chequeBook.setNumStart(chequeBookRequest.getNumStart());
        chequeBook.setNumEnd(chequeBookRequest.getNumEnd());
        chequeBook.setFlagRemit(chequeBookRequest.getFlagRemit());
        chequeBook.setDisableDate(chequeBookRequest.getDisableDate());
        chequeBook.setChequeBookDate(chequeBookRequest.getChequeBookDate());
        chequeBookRepository.save(chequeBook);
        chequeBookRepository.flush();
        chequeBookRepository.findByChequeAndNumEndAndNumStart(chequeBookRequest.getNumEnd(), chequeBookRequest.getNumStart())
                .forEach(aLong -> {
                    List<Long> chequeUnique = chequeRepository.findByChequeBookAndChequeNumberAndChequeBookId(chequeBookRequest.getNumStart() + (aLong - 1), chequeBookRequest.getChequeBookId());
                    if (chequeUnique.size() > 1) {
                        throw new RuleException("این برگه چک قبلا ثبت شده است. ");
                    }
                    Cheque cheque = new Cheque();
                    cheque.setChequeNumber(chequeBookRequest.getNumStart() + (aLong - 1));
                    cheque.setChequeUseType(chequeUseTypeRepository.getOne(1L));
                    cheque.setDescription(" ");
                    cheque.setFlagRemit(chequeBookRequest.getFlagRemit());
                    cheque.setChequeBook(chequeBook);
                    cheque.setChequeStatus(chequeStatusRepository.getOne(1L));
                    chequeRepository.save(cheque);
                });

        return true;
    }

}
