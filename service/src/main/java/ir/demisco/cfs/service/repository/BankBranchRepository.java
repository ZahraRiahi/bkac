package ir.demisco.cfs.service.repository;

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

    @Query("select coalesce(COUNT(br.id),0) from BKACBankBranch br  where br.code=:code and  br.id not in (:id) and br.bank.id=:bankId and br.deletedDate is null ")
    Long getCountBankBranchByCodeAndBankIdAndDeletedDate(String code, Long id, Long bankId);

    @Query(value = "select count(t.id)" +
            "  from bkac.BANK_BRANCH t" +
            "  left join bkac.bank_account ba" +
            "    on t.id = ba.bank_branch_id" +
            "  left join rprq.request_detail_info rdi" +
            "    on t.id = rdi.bank_branch_id" +
            " where t.id  = :bankBranchId" +
            "   and (ba.bank_branch_id = :bankBranchId or rdi.bank_branch_id = :bankBranchId)", nativeQuery = true)
    long getCountByIdForDelete(Long bankBranchId);
}
