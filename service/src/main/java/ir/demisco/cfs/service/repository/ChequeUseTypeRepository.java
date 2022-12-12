package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.ChequeUseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChequeUseTypeRepository extends JpaRepository<ChequeUseType, Long> {
    @Query(value = " SELECT T.ID, T.CODE, T.DESCRIPTION " +
            "  FROM BKAC.CHEQUE_USE_TYPE T " +
            " WHERE (:chequeUseTypeCodeObject IS NULL OR T.CODE = :chequeUseTypeCode) " +
            "  AND (:chequeUseType IS NULL OR T.ID = :chequeUseTypeId) ", nativeQuery = true)
    List<Object[]> findByChequeUseTypeByCodeAndId(Object chequeUseTypeCodeObject,String chequeUseTypeCode,Object chequeUseType,Long chequeUseTypeId);
}
