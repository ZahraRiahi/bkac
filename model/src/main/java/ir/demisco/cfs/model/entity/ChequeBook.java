package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cheque_Book", schema = "bkac")
public class ChequeBook  extends AuditModel<Long> {
    private Long id;
    private Long numStart;
    private Long numEnd;
    private Boolean flagRemit;
    private Date disableDate;
    private BankAccount bankAccount;
    private ChequeBookType chequeBookType;
    private LocalDateTime deletedDate;
    private LocalDateTime chequeBookDate;

    @Id
    @SequenceGenerator(schema = "bkac", name = "cheque_Book_generator", sequenceName = "sq_cheque_Book", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_Book_generator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NUM_START")
    public Long getNumStart() {
        return numStart;
    }

    public void setNumStart(Long numStart) {
        this.numStart = numStart;
    }

    @Column(name = "NUM_END")
    public Long getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(Long numEnd) {
        this.numEnd = numEnd;
    }

    @Column(name = "FLAG_REMIT")
    public Boolean getFlagRemit() {
        return flagRemit;
    }

    public void setFlagRemit(Boolean flagRemit) {
        this.flagRemit = flagRemit;
    }

    @Column(name = "DISABLE_DATE")
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
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
    @JoinColumn(name = "CHEQUE_BOOK_TYPE_ID")
    public ChequeBookType getChequeBookType() {
        return chequeBookType;
    }

    public void setChequeBookType(ChequeBookType chequeBookType) {
        this.chequeBookType = chequeBookType;
    }

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Column(name = "CHEQUE_BOOK_DATE")
    public LocalDateTime getChequeBookDate() {
        return chequeBookDate;
    }

    public void setChequeBookDate(LocalDateTime chequeBookDate) {
        this.chequeBookDate = chequeBookDate;
    }
}
