package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query("select ba from  BKACBankAccount ba where ba.bankBranch.id=:bankBranchId and ba.deletedDate is null")
    List<BankAccount> findByBankBranchId(Long bankBranchId);

    @Query(" select coalesce(COUNT(ba.id),0) from BKACBankAccount ba  where ba.accountCodeSheba=:accountCodeSheba and ba.deletedDate is null ")
    Long getCountByBankAccountByAccountCodeSheba(String accountCodeSheba);

    @Query(" select coalesce(COUNT(ba.id),0) from BKACBankAccount ba  where ba.code=:code and ba.deletedDate is null and ba.bank.id=:bankId and ba.bankAccountType.id=:bankAccountTypeId ")
    Long getCountByBankAccountByCodeAndBankAccountTypeAndBank(String code, Long bankId, Long bankAccountTypeId);
    @Query(value = "SELECT BACC.ID as BANK_ACCOUNT_ID," +
            "       BACC.CODE as BANK_ACCOUNT_CODE," +
            "       BACC.DESCRIPTION BANK_ACCOUNT_DESCRIPTION," +
            "      BACC.CODE  || bnk.NAME BANK_ACCOUNT_NAME," +
            "       BACC.BANK_ID," +
            "       BNK.NAME AS BANK_NAME," +
            "       BANK_BRANCH_ID," +
            "       BRCH.NAME AS BRANCH_NAME," +
            "       BACC.DISABLE_DATE," +
            "       CASE" +
            "         WHEN BACC.DISABLE_DATE IS NULL THEN" +
            "          1" +
            "         ELSE" +
            "          0" +
            "       END ACTIVE_FLAG" +
            "  FROM bkac.BANK_ACCOUNT BACC" +
            " INNER JOIN BKAC.BANK BNK" +
            "    ON BACC.BANK_ID = BNK.ID" +
            " INNER JOIN BKAC.BANK_BRANCH BRCH" +
            "    ON BRCH.ID = BACC.BANK_BRANCH_ID" +
            " INNER JOIN FNAC.FINANCIAL_ACCOUNT FNC" +
            "    ON FNC.ID = BACC.FINANCIAL_ACCOUNT_ID" +
            " INNER JOIN BKAC.BANK_ACCOUNT_TYPE BACT" +
            "    ON BACT.ID = BACC.BANK_ACCOUNT_TYPE_ID" +
            "  LEFT OUTER JOIN FNCR.MONEY_TYPE MNTY" +
            "    ON MNTY.ID = BACC.MONEY_TYPE_ID" +
            " WHERE (BACC.BANK_ID =:bankId OR :bankIdObject IS NULL)" +
            " AND BACC.ORGANIZATION_ID = :organizationId "
            , nativeQuery = true)
    List<Object[]> findByBakAndIdAndOrganization(Long bankId,Object bankIdObject,Long organizationId);
}
