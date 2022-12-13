package ir.demisco.cfs.model.dto.request;

import java.time.LocalDateTime;

public class ChequeStatusListRequest {
    private Long id;
    private Long chequeNumber;
    private LocalDateTime useDate;
    private Long chequeUseTypeId;
    private String nationalCode;
    private String description;
    private Double amount;
    private Boolean flagRemit;
    private Long chequeBookId;
    private Long chequeStatusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(Long chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public LocalDateTime getUseDate() {
        return useDate;
    }

    public void setUseDate(LocalDateTime useDate) {
        this.useDate = useDate;
    }

    public Long getChequeUseTypeId() {
        return chequeUseTypeId;
    }

    public void setChequeUseTypeId(Long chequeUseTypeId) {
        this.chequeUseTypeId = chequeUseTypeId;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getFlagRemit() {
        return flagRemit;
    }

    public void setFlagRemit(Boolean flagRemit) {
        this.flagRemit = flagRemit;
    }

    public Long getChequeBookId() {
        return chequeBookId;
    }

    public void setChequeBookId(Long chequeBookId) {
        this.chequeBookId = chequeBookId;
    }

    public Long getChequeStatusId() {
        return chequeStatusId;
    }

    public void setChequeStatusId(Long chequeStatusId) {
        this.chequeStatusId = chequeStatusId;
    }
}
