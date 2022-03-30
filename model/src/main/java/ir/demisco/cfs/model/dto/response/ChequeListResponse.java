package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class ChequeListResponse {
    private Long chequeId;
    private Long chequeNumber;
    private LocalDateTime useDate;
    private String description;
    private Long chequeUseTypeId;
    private String chequeUseTypeDescription;
    private String nationalCode;
    private Double amount;
    private Boolean flagRemit;
    private Long chequeBookId;
    private Long chequeStatusId;
    private String chequeStatusDescription;

    public Long getChequeId() {
        return chequeId;
    }

    public void setChequeId(Long chequeId) {
        this.chequeId = chequeId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getChequeUseTypeId() {
        return chequeUseTypeId;
    }

    public void setChequeUseTypeId(Long chequeUseTypeId) {
        this.chequeUseTypeId = chequeUseTypeId;
    }

    public String getChequeUseTypeDescription() {
        return chequeUseTypeDescription;
    }

    public void setChequeUseTypeDescription(String chequeUseTypeDescription) {
        this.chequeUseTypeDescription = chequeUseTypeDescription;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
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

    public String getChequeStatusDescription() {
        return chequeStatusDescription;
    }

    public void setChequeStatusDescription(String chequeStatusDescription) {
        this.chequeStatusDescription = chequeStatusDescription;
    }

    public static ChequeListResponse.Builder builder() {
        return new ChequeListResponse.Builder();
    }

    public static final class Builder {
        private ChequeListResponse chequeListResponse;

        private Builder() {
            chequeListResponse = new ChequeListResponse();
        }

        public static Builder chequeListResponse() {
            return new Builder();
        }

        public Builder chequeId(Long chequeId) {
            chequeListResponse.setChequeId(chequeId);
            return this;
        }

        public Builder chequeNumber(Long chequeNumber) {
            chequeListResponse.setChequeNumber(chequeNumber);
            return this;
        }

        public Builder useDate(LocalDateTime useDate) {
            chequeListResponse.setUseDate(useDate);
            return this;
        }

        public Builder description(String description) {
            chequeListResponse.setDescription(description);
            return this;
        }

        public Builder chequeUseTypeId(Long chequeUseTypeId) {
            chequeListResponse.setChequeUseTypeId(chequeUseTypeId);
            return this;
        }

        public Builder chequeUseTypeDescription(String chequeUseTypeDescription) {
            chequeListResponse.setChequeUseTypeDescription(chequeUseTypeDescription);
            return this;
        }

        public Builder nationalCode(String nationalCode) {
            chequeListResponse.setNationalCode(nationalCode);
            return this;
        }

        public Builder amount(Double amount) {
            chequeListResponse.setAmount(amount);
            return this;
        }

        public Builder flagRemit(Boolean flagRemit) {
            chequeListResponse.setFlagRemit(flagRemit);
            return this;
        }

        public Builder chequeBookId(Long chequeBookId) {
            chequeListResponse.setChequeBookId(chequeBookId);
            return this;
        }

        public Builder chequeStatusId(Long chequeStatusId) {
            chequeListResponse.setChequeStatusId(chequeStatusId);
            return this;
        }

        public Builder chequeStatusDescription(String chequeStatusDescription) {
            chequeListResponse.setChequeStatusDescription(chequeStatusDescription);
            return this;
        }

        public ChequeListResponse build() {
            return chequeListResponse;
        }
    }
}
