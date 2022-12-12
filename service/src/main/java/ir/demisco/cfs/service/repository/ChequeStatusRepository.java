package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.ChequeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChequeStatusRepository extends JpaRepository<ChequeStatus, Long> {
    @Query(value = " SELECT T.ID, T.CODE, T.DESCRIPTION " +
            "  FROM BKAC.CHEQUE_STATUS  T " +
            " WHERE (:chequeStatusCodeObject IS NULL OR T.CODE = :chequeStatusCode) " +
            "  AND (:chequeStatus IS NULL OR T.ID = :chequeStatusId) ", nativeQuery = true)
    List<Object[]> findByChequeStatusByCodeAndId(Object chequeStatusCodeObject, String chequeStatusCode, Object chequeStatus, Long chequeStatusId);
}
