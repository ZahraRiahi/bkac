package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "cheque_status", schema = "bkac")
public class ChequeStatus extends AuditModel<Long> {
    private Long id;
    private String description;
    private LocalDateTime deletedDate;
    @Override
    @Id
    @SequenceGenerator(schema = "bkac", name = "cheque_status_generator", sequenceName = "sq_cheque_status_Type", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_status_generator")
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

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }
}
