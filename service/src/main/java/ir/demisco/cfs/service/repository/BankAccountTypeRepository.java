package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.BankAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankAccountTypeRepository extends JpaRepository<BankAccountType, Long> {
    @Query("select bac from BKACBankAccountType bac where bac.deletedDate is null and bac.disableDate is null ")
    List<BankAccountType> getBankAccountTypeList();
}
