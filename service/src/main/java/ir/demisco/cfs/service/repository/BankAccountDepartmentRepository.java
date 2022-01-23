package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.BankAccountDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountDepartmentRepository extends JpaRepository<BankAccountDepartment, Long> {
}
