package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.BankAccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountTypeRepository extends JpaRepository<BankAccountType, Long> {
}
