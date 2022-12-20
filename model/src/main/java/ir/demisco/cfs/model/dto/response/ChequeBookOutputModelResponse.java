package ir.demisco.cfs.model.dto.response;

public class ChequeBookOutputModelResponse {
    private Long chequeBookId;
    private String chequeBookTypeDescription;
    private Boolean activeFlag;

    public Long getChequeBookId() {
        return chequeBookId;
    }

    public void setChequeBookId(Long chequeBookId) {
        this.chequeBookId = chequeBookId;
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
    public static ChequeBookOutputModelResponse.Builder builder() {
        return new ChequeBookOutputModelResponse.Builder();
    }
    public static final class Builder {
        private ChequeBookOutputModelResponse chequeBookOutputModelResponse;

        private Builder() {
            chequeBookOutputModelResponse = new ChequeBookOutputModelResponse();
        }

        public static Builder aChequeBookOutputModelResponse() {
            return new Builder();
        }

        public Builder chequeBookId(Long chequeBookId) {
            chequeBookOutputModelResponse.setChequeBookId(chequeBookId);
            return this;
        }

        public Builder chequeBookTypeDescription(String chequeBookTypeDescription) {
            chequeBookOutputModelResponse.setChequeBookTypeDescription(chequeBookTypeDescription);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            chequeBookOutputModelResponse.setActiveFlag(activeFlag);
            return this;
        }

        public ChequeBookOutputModelResponse build() {
            return chequeBookOutputModelResponse;
        }
    }
}
