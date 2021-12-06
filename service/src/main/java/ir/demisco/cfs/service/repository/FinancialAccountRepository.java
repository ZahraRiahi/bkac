package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {
}
