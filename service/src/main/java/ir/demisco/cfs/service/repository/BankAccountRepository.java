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
}
