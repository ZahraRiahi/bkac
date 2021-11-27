package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.Bank;
import ir.demisco.cfs.model.entity.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BankBranchRepository extends JpaRepository<BankBranch, Long> {
    @Query(" select br.id,b.id,br.name,br.code from BKACBankBranch br " +
            " join br.bank b" +
            " where  br.bank.id=:bankId and br.deletedDate is null ")
    List<Object[]> findByBankBranchByBankId(Long bankId);

    @Query(" select coalesce(COUNT(br.id),0) from BKACBankBranch br  where br.code=:code and br.deletedDate is null and br.bank.id=:bankId ")
    Long getCountByBankBranchAndCodeAndDeletedDateAndBank(String code, Long bankId);

    @Query("select br from  BKACBankBranch br where br.bank.id=:bankBranchId and br.deletedDate is null")
    List<BankBranch> findByBankId(Long bankBranchId);
}
