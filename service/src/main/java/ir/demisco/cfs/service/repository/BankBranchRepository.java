package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankBranchRepository  extends JpaRepository<BankBranch, Long> {
    @Query("select coalesce(COUNT(b.id),0) from BKACBank b  where b.code=:code and b.deletedDate is null ")
    Long getCountByBankAndCodeAndDeletedDate(String code);
}
