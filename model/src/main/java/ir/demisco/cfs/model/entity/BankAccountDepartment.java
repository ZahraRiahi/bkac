package ir.demisco.cfs.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_account_department", schema = "bkac")
public class BankAccountDepartment {
    private Long id;
    private BankAccount bankAccount;
    private FinancialDepartment financialDepartment;
    private LocalDateTime deletedDate;

    @Id
    @SequenceGenerator(schema = "bkac", name = "bank_account_department_generator", sequenceName = "sq_bank_account_department", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_account_department_generator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ACCOUNT_ID")
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_DEPARTMENT_ID")
    public FinancialDepartment getFinancialDepartment() {
        return financialDepartment;
    }

    public void setFinancialDepartment(FinancialDepartment financialDepartment) {
        this.financialDepartment = financialDepartment;
    }
    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }
}
