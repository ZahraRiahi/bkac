package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select c.chequeStatus.id from Cheque c  where c.id=:chequeId ")
    Long getChequeStatusByChequeId(Long chequeId);

    @Query("select 1 from  Cheque ch where ch.chequeBook.id=:chequeBookId and ch.chequeStatus.id>1 and ch.deletedDate is null")
    Long findByChequeAndChequeBookIdAndChequeStatusId(Long chequeBookId);

    List<Cheque> findByChequeBookId(Long chequeBookId);

    @Query("select coalesce(COUNT(c.id),0) from  Cheque c where c.chequeNumber=:chequeNumber and c.deletedDate is null and c.chequeBook.id=:chequeBookId")
    List<Long> findByChequeBookAndChequeNumberAndChequeBookId(Long chequeNumber, Long chequeBookId);

    @Query(nativeQuery = true, value = " SELECT bkac.NUM_TO_WORDS(:p_Number) FROM dual")
    String getFuction(Long p_Number);


}
