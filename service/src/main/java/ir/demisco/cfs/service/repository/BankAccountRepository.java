package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query("select ba from  BKACBankAccount ba where ba.bankBranch.id=:bankBranchId and ba.deletedDate is null")
    List<BankAccount> findByBankBranchId(Long bankBranchId);
}
