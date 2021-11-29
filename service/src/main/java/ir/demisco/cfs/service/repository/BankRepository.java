package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {
    @Query("select coalesce(COUNT(b.id),0) from BKACBank b  where b.code=:code and b.deletedDate is null ")
    Long getCountByBankAndCodeAndDeletedDate(String code);

    @Query("select coalesce(COUNT(b.id),0) from BKACBank b  where b.code=:code and b.id not in (:id) and b.deletedDate is null ")
    Long getCountBankByCodeAndIdAndDeletedDate(String code, Long id);


}
