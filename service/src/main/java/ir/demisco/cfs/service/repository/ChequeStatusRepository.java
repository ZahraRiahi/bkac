package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.ChequeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeStatusRepository extends JpaRepository<ChequeStatus, Long> {
}
