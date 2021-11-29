package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.BankAccount;
import ir.demisco.cfs.model.entity.ChequeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChequeBookRepository extends JpaRepository<ChequeBook, Long> {
    @Query("select cb from  ChequeBook cb where cb.chequeBookType.id=:chequeBookTypeId and cb.deletedDate is null")
    List<ChequeBook> findByChequeBookTypeId(Long chequeBookTypeId);

}
