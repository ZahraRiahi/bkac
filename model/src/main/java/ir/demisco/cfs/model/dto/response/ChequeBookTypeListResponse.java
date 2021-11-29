package ir.demisco.cfs.model.dto.response;

import java.util.Date;

public class ChequeBookTypeListResponse {
    private Long chequeBookTypeId;
    private String description;
    private Boolean flagRemit;
    private Date disableDate;
    private Boolean activeFlag;
    private String subTitle;
    private Long chequeCount;

    public Long getChequeBookTypeId() {
        return chequeBookTypeId;
    }

    public void setChequeBookTypeId(Long chequeBookTypeId) {
        this.chequeBookTypeId = chequeBookTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Long getChequeCount() {
        return chequeCount;
    }

    public void setChequeCount(Long chequeCount) {
        this.chequeCount = chequeCount;
    }

    public static ChequeBookTypeListResponse.Builder builder() {
        return new ChequeBookTypeListResponse.Builder();
    }

    public static final class Builder {
        private ChequeBookTypeListResponse chequeBookTypeListResponse;

        private Builder() {
            chequeBookTypeListResponse = new ChequeBookTypeListResponse();
        }

        public static Builder chequeBookTypeListResponse() {
            return new Builder();
        }

        public Builder chequeBookTypeId(Long chequeBookTypeId) {
            chequeBookTypeListResponse.setChequeBookTypeId(chequeBookTypeId);
            return this;
        }

        public Builder description(String description) {
            chequeBookTypeListResponse.setDescription(description);
            return this;
        }

        public Builder flagRemit(Boolean flagRemit) {
            chequeBookTypeListResponse.setFlagRemit(flagRemit);
            return this;
        }

        public Builder disableDate(Date disableDate) {
            chequeBookTypeListResponse.setDisableDate(disableDate);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            chequeBookTypeListResponse.setActiveFlag(activeFlag);
            return this;
        }

        public Builder subTitle(String subTitle) {
            chequeBookTypeListResponse.setSubTitle(subTitle);
            return this;
        }

        public Builder chequeCount(Long chequeCount) {
            chequeBookTypeListResponse.setChequeCount(chequeCount);
            return this;
        }

        public ChequeBookTypeListResponse build() {
            return chequeBookTypeListResponse;
        }
    }
}
