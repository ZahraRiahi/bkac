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
@Table(name = "cheque_Book_Type", schema = "bkac")
public class ChequeBookType extends AuditModel<Long> {
    private Long id;
    private String description;
    private Boolean flagRemit;
    private Date disableDate;
    private LocalDateTime deletedDate;
    private Bank bank;
    private String subTitle;
    private Long chequeCount;
    @Override
    @Id
    @SequenceGenerator(schema = "bkac", name = "cheque_Book_Type_generator", sequenceName = "sq_cheque_Book_Type", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_Book_Type_generator")
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID")
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Column(name = "SUBTITLE")
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Column(name = "CHEQUE_COUNT")
    public Long getChequeCount() {
        return chequeCount;
    }

    public void setChequeCount(Long chequeCount) {
        this.chequeCount = chequeCount;
    }
}
