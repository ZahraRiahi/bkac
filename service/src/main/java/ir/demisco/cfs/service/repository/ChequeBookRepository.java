package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.BankAccount;
import ir.demisco.cfs.model.entity.ChequeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChequeBookRepository extends JpaRepository<ChequeBook, Long> {
    @Query("select cb from  ChequeBook cb where cb.chequeBookType.id=:chequeBookTypeId and cb.deletedDate is null")
    List<ChequeBook> findByChequeBookTypeId(Long chequeBookTypeId);

    @Query("select coalesce(COUNT(cb.id),0) from  ChequeBook cb where cb.bankAccount.id=:bankAccountId and cb.numStart=:numStart  and cb.deletedDate is null ")
    Long findByChequeBookAndBankAccountAndNumStart(Long bankAccountId, Long numStart);

    @Query(value = " SELECT LEVEL LVL " +
            "            FROM DUAL " +
            "          CONNECT BY LEVEL <= :numEnd - :numStart + 1 "
            , nativeQuery = true)
    List<Long> findByChequeAndNumEndAndNumStart(Long numEnd, Long numStart);
}
