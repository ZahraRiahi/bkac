package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cheque_use_Type", schema = "bkac")
public class ChequeUseType extends AuditModel<Long> {
    private Long id;
    private String code;
    private String description;
    private LocalDateTime deletedDate;

    @Id
    @SequenceGenerator(schema = "bkac", name = "cheque_use_Type_generator", sequenceName = "sq_cheque_use_Type", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_use_Type_generator")
    public Long getId() {
        return id;
    }

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
