package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.ChequeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
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

    @Query(value = " SELECT CHBK.ID CHECK_BOOK_ID," +
            "       CHBT.DESCRIPTION || ' - ' || CHBK.CHEQUE_BOOK_DATE AS CHEQUE_BOOK_TYP_DESCRIPTION ," +
            "       CASE " +
            "         WHEN CHBK.DISABLE_DATE IS NULL THEN " +
            "          1 " +
            "         ELSE " +
            "          0 " +
            "       END ACTIVE_FLAG " +
            "  FROM bkac.CHEQUE_BOOK CHBK " +
            " INNER JOIN BKAC.CHEQUE_BOOK_TYPE CHBT " +
            "    ON CHBK.CHEQUE_BOOK_TYPE_ID = CHBT.ID " +
            " INNER JOIN BKAC.BANK_ACCOUNT BA " +
            "    ON BA.ID = CHBK.BANK_ACCOUNT_ID " +
            "   AND BA.ORGANIZATION_ID = :organizationId " +
            " WHERE (:bankAccountObject IS NULL OR CHBK.BANK_ACCOUNT_ID = :bankAccountId) " +
            "   AND BA.DISABLE_DATE IS NULL" +
            "   AND (CHBK.CHEQUE_BOOK_DATE BETWEEN (CASE" +
            "         WHEN :fromDateObject IS NULL THEN" +
            "          CHBK.CHEQUE_BOOK_DATE" +
            "         ELSE" +
            "          :fromDate " +
            "       END) AND (CASE" +
            "         WHEN :toDateObject IS NULL THEN" +
            "          CHBK.CHEQUE_BOOK_DATE" +
            "         ELSE" +
            "          :toDate " +
            "       END)) "
            , nativeQuery = true)
    List<Object[]> findByChequeBookByOrgAndFromAndToDate(Long organizationId, Object bankAccountObject, Long bankAccountId,
                                                    Object   fromDateObject,LocalDateTime fromDate,Object toDateObject,LocalDateTime toDate);
}
