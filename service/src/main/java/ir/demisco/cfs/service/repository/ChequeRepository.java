package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select c.chequeStatus.id from Cheque c  where c.id=:chequeId ")
    Long getChequeStatusByChequeId(Long chequeId);
}
