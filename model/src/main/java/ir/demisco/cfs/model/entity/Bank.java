package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "BKACBank")
@Table(name = "bank", schema = "bkac")
public class Bank extends AuditModel<Long> {
    private Long id;
    private String code;
    private String name;
    private Date disableDate;
    private LocalDateTime deletedDate;
    private String formatNumber;
    private String iconName;

    @Override
    @Id
    @SequenceGenerator(schema = "bkac", name = "bank_generator", sequenceName = "sq_bank", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_generator")
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Column(name = "FORMAT_NUMBER")
    public String getFormatNumber() {
        return formatNumber;
    }

    public void setFormatNumber(String formatNumber) {
        this.formatNumber = formatNumber;
    }

    @Column(name = "ICON_NAME")
    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
