package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cheque", schema = "bkac")
public class Cheque extends AuditModel<Long> {
    private Long id;
    private Long chequeNumber;
    private LocalDateTime useDate;
    private ChequeUseType chequeUseType;
    private String nationalCode;
    private String description;
    private Double amount;
    private Boolean flagRemit;
    private ChequeBook chequeBook;
    private ChequeStatus chequeStatus;
    private LocalDateTime deletedDate;

    @Id
    @SequenceGenerator(schema = "bkac", name = "cheque_generator", sequenceName = "sq_cheque", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_generator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CHEQUE_NUMBER")
    public Long getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(Long chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    @Column(name = "USE_DATE")
    public LocalDateTime getUseDate() {
        return useDate;
    }

    public void setUseDate(LocalDateTime useDate) {
        this.useDate = useDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHEQUE_USE_TYPE_ID")
    public ChequeUseType getChequeUseType() {
        return chequeUseType;
    }

    public void setChequeUseType(ChequeUseType chequeUseType) {
        this.chequeUseType = chequeUseType;
    }

    @Column(name = "NATIONAL_CODE")
    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "AMOUNT")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name = "FLAG_REMIT")
    public Boolean getFlagRemit() {
        return flagRemit;
    }

    public void setFlagRemit(Boolean flagRemit) {
        this.flagRemit = flagRemit;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHEQUE_BOOK_ID")
    public ChequeBook getChequeBook() {
        return chequeBook;
    }

    public void setChequeBook(ChequeBook chequeBook) {
        this.chequeBook = chequeBook;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHEQUE_STATUS_ID")
    public ChequeStatus getChequeStatus() {
        return chequeStatus;
    }

    public void setChequeStatus(ChequeStatus chequeStatus) {
        this.chequeStatus = chequeStatus;
    }

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }
}
