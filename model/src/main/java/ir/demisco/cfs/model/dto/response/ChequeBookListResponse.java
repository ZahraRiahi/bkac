package ir.demisco.cfs.model.dto.response;

import java.util.Date;

public class ChequeBookListResponse {
    private Long chequeBookId;
    private Long numStart;
    private Long numEnd;
    private Boolean flagRemit;
    private Date disableDate;
    private Long bankAccountId;
    private Long chequeBookTypeId;
    private String chequeBookTypeDescription;
    private Boolean activeFlag;
    private Long chequeCount;

    public Long getChequeBookId() {
        return chequeBookId;
    }

    public void setChequeBookId(Long chequeBookId) {
        this.chequeBookId = chequeBookId;
    }

    public Long getNumStart() {
        return numStart;
    }

    public void setNumStart(Long numStart) {
        this.numStart = numStart;
    }

    public Long getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(Long numEnd) {
        this.numEnd = numEnd;
    }

    public Boolean getFlagRemit() {
        return flagRemit;
    }

    public void setFlagRemit(Boolean flagRemit) {
        this.flagRemit = flagRemit;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Long getChequeBookTypeId() {
        return chequeBookTypeId;
    }

    public void setChequeBookTypeId(Long chequeBookTypeId) {
        this.chequeBookTypeId = chequeBookTypeId;
    }

    public String getChequeBookTypeDescription() {
        return chequeBookTypeDescription;
    }

    public void setChequeBookTypeDescription(String chequeBookTypeDescription) {
        this.chequeBookTypeDescription = chequeBookTypeDescription;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getChequeCount() {
        return chequeCount;
    }

    public void setChequeCount(Long chequeCount) {
        this.chequeCount = chequeCount;
    }

    public static ChequeBookListResponse.Builder builder() {
        return new ChequeBookListResponse.Builder();
    }

    public static final class Builder {
        private ChequeBookListResponse chequeBookListResponse;

        private Builder() {
            chequeBookListResponse = new ChequeBookListResponse();
        }

        public static Builder chequeBookListResponse() {
            return new Builder();
        }

        public Builder chequeBookId(Long chequeBookId) {
            chequeBookListResponse.setChequeBookId(chequeBookId);
            return this;
        }

        public Builder numStart(Long numStart) {
            chequeBookListResponse.setNumStart(numStart);
            return this;
        }

        public Builder numEnd(Long numEnd) {
            chequeBookListResponse.setNumEnd(numEnd);
            return this;
        }

        public Builder flagRemit(Boolean flagRemit) {
            chequeBookListResponse.setFlagRemit(flagRemit);
            return this;
        }

        public Builder disableDate(Date disableDate) {
            chequeBookListResponse.setDisableDate(disableDate);
            return this;
        }

        public Builder bankAccountId(Long bankAccountId) {
            chequeBookListResponse.setBankAccountId(bankAccountId);
            return this;
        }

        public Builder chequeBookTypeId(Long chequeBookTypeId) {
            chequeBookListResponse.setChequeBookTypeId(chequeBookTypeId);
            return this;
        }

        public Builder chequeBookTypeDescription(String chequeBookTypeDescription) {
            chequeBookListResponse.setChequeBookTypeDescription(chequeBookTypeDescription);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            chequeBookListResponse.setActiveFlag(activeFlag);
            return this;
        }

        public Builder chequeCount(Long chequeCount) {
            chequeBookListResponse.setChequeCount(chequeCount);
            return this;
        }

        public ChequeBookListResponse build() {
            return chequeBookListResponse;
        }
    }
}
