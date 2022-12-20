package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.ChequeBookChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.ChequeBookFilterModelRequest;
import ir.demisco.cfs.model.dto.request.ChequeBookRequest;
import ir.demisco.cfs.model.dto.response.ChequeBookListResponse;
import ir.demisco.cfs.model.dto.response.ChequeBookOutputModelResponse;
import ir.demisco.cfs.model.entity.Cheque;
import ir.demisco.cfs.model.entity.ChequeBook;
import ir.demisco.cfs.service.api.ChequeBookService;
import ir.demisco.cfs.service.repository.BankAccountRepository;
import ir.demisco.cfs.service.repository.ChequeBookRepository;
import ir.demisco.cfs.service.repository.ChequeBookTypeRepository;
import ir.demisco.cfs.service.repository.ChequeRepository;
import ir.demisco.cfs.service.repository.ChequeStatusRepository;
import ir.demisco.cfs.service.repository.ChequeUseTypeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            if (chequeBookListResponse.getDisableDate() == null) {
                chequeBookListResponse.setActiveFlag(true);
            }
            chequeBookListResponses.add(chequeBookListResponse);
        }
        dataSourceResult.setData(chequeBookListResponses);
        return dataSourceResult;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteChequeBook(Long chequeBookId) {
        Long cheques = chequeRepository.findByChequeAndChequeBookIdAndChequeStatusId(chequeBookId);
        if (cheques != null) {
            throw new RuleException("fin.chequeBook.delete");
        } else {
            List<Cheque> chequeList = chequeRepository.findByChequeBookId(chequeBookId);
            chequeList.forEach(e -> chequeRepository.deleteById(e.getId()));
            ChequeBook chequeBook = chequeBookRepository.findById(chequeBookId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
            chequeBookRepository.deleteById(chequeBook.getId());
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
        if (Boolean.TRUE.equals(!chequeBookChangeStatusRequest.getActiveFlag())) {
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
        chequeBook.setBankAccount(
                bankAccountRepository.getOne(chequeBookRequest.getBankAccountId()));
        chequeBook.setChequeBookType(
                chequeBookTypeRepository.getOne(chequeBookRequest.getChequeBookTypeId()));
        chequeBook.setNumStart(chequeBookRequest.getNumStart());
        chequeBook.setNumEnd(chequeBookRequest.getNumEnd());
        chequeBook.setFlagRemit(chequeBookRequest.getFlagRemit());
        chequeBook.setDisableDate(chequeBookRequest.getDisableDate());
        chequeBook.setChequeBookDate(chequeBookRequest.getChequeBookDate());//trunc
        chequeBookRepository.save(chequeBook);
        chequeBookRepository.findByChequeAndNumEndAndNumStart(chequeBookRequest.getNumEnd(), chequeBookRequest.getNumStart())
                .forEach((Long aLong) -> {
                    List<Long> chequeUnique = chequeRepository.findByChequeBookAndChequeNumberAndChequeBookId(chequeBookRequest.getNumStart() + (aLong - 1), chequeBookRequest.getChequeBookId());
                    if (chequeUnique.size() > 1) {
                        throw new RuleException("fin.chequeBook.useCheque");
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

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<ChequeBookOutputModelResponse> getChequeBookList(ChequeBookFilterModelRequest chequeBookFilterModelRequest) {
        Object bankAccountObject = null;
        if (chequeBookFilterModelRequest.getBankAccountId() != null) {
            bankAccountObject = "bankAccountObject";
        } else {
            chequeBookFilterModelRequest.setBankAccountId(0L);
        }
        Object fromDateObject = null;
        if (chequeBookFilterModelRequest.getFromDate() != null) {
            fromDateObject = "fromDateObject";
        } else {
            chequeBookFilterModelRequest.setFromDate(LocalDateTime.now());
        }
        Object toDateObject = null;
        if (chequeBookFilterModelRequest.getToDate() != null) {
            toDateObject = "toDateObject";
        } else {
            chequeBookFilterModelRequest.setToDate(LocalDateTime.now());
        }
        List<Object[]> chequeBookObject = chequeBookRepository.findByChequeBookByOrgAndFromAndToDate(SecurityHelper.getCurrentUser().getOrganizationId(), bankAccountObject,
                chequeBookFilterModelRequest.getBankAccountId(),fromDateObject,chequeBookFilterModelRequest.getFromDate(),toDateObject,chequeBookFilterModelRequest.getToDate());
        return chequeBookObject.stream().map(objects -> ChequeBookOutputModelResponse.builder().chequeBookId(Long.parseLong(objects[0].toString()))
                .chequeBookTypeDescription(objects[1].toString())
                .activeFlag(Integer.parseInt(objects[2].toString()) == 1)
                .build()).collect(Collectors.toList());
    }

}
