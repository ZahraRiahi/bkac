package ir.demisco.cfs.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "BKACBankAccountType")
@Table(name = "bank_account_type", schema = "bkac")
public class BankAccountType {
    private Long id;
    private String description;
    private Date disableDate;
    private LocalDateTime deletedDate;

    @Id
    @SequenceGenerator(schema = "bkac", name = "bank_account_type_generator", sequenceName = "sq_bank_account_type", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_account_type_generator")
    public Long getId() {
        return id;
    }

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
}
