package ir.demisco.cfs.model.dto.response;

import java.util.Date;

public class BankAccountOutputModelResponse {
    private Long bankAccountId;
    private String bankAccountCode;
    private String bankAccountDescription;
    private Long bankId;
    private String bankName;
    private Long bankBranchId;
    private String branchName;
    private Date disableDate;
    private Boolean activeFlag;
    private String bankAccountName;

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankAccountCode() {
        return bankAccountCode;
    }

    public void setBankAccountCode(String bankAccountCode) {
        this.bankAccountCode = bankAccountCode;
    }

    public String getBankAccountDescription() {
        return bankAccountDescription;
    }

    public void setBankAccountDescription(String bankAccountDescription) {
        this.bankAccountDescription = bankAccountDescription;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(Long bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public static BankAccountOutputModelResponse.Builder builder() {
        return new BankAccountOutputModelResponse.Builder();
    }

    public static final class Builder {
        private BankAccountOutputModelResponse bankAccountOutputModelResponse;

        private Builder() {
            bankAccountOutputModelResponse = new BankAccountOutputModelResponse();
        }

        public static Builder bankAccountOutputModelResponse() {
            return new Builder();
        }

        public Builder bankAccountId(Long bankAccountId) {
            bankAccountOutputModelResponse.setBankAccountId(bankAccountId);
            return this;
        }

        public Builder bankAccountCode(String bankAccountCode) {
            bankAccountOutputModelResponse.setBankAccountCode(bankAccountCode);
            return this;
        }

        public Builder bankAccountDescription(String bankAccountDescription) {
            bankAccountOutputModelResponse.setBankAccountDescription(bankAccountDescription);
            return this;
        }

        public Builder bankId(Long bankId) {
            bankAccountOutputModelResponse.setBankId(bankId);
            return this;
        }

        public Builder bankName(String bankName) {
            bankAccountOutputModelResponse.setBankName(bankName);
            return this;
        }

        public Builder bankBranchId(Long bankBranchId) {
            bankAccountOutputModelResponse.setBankBranchId(bankBranchId);
            return this;
        }

        public Builder branchName(String branchName) {
            bankAccountOutputModelResponse.setBranchName(branchName);
            return this;
        }

        public Builder disableDate(Date disableDate) {
            bankAccountOutputModelResponse.setDisableDate(disableDate);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            bankAccountOutputModelResponse.setActiveFlag(activeFlag);
            return this;
        }

        public Builder bankAccountName(String bankAccountName) {
            bankAccountOutputModelResponse.setBankAccountName(bankAccountName);
            return this;
        }

        public BankAccountOutputModelResponse build() {
            return bankAccountOutputModelResponse;
        }
    }
}
