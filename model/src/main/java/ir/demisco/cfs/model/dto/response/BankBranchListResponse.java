package ir.demisco.cfs.model.dto.response;

import java.util.Date;

public class BankBranchListResponse {
    private Long bankId;
    private Long branchId;
    private String branchCode;
    private String branchName;
    private String telNumber;
    private String faxNumber;
    private String boxCode;
    private String postCode;
    private Date disableDate;
    private Boolean activeFlag;
    private String branchAddress;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
    public static BankBranchListResponse.Builder builder() {
        return new BankBranchListResponse.Builder();
    }

    public static final class Builder {
        private BankBranchListResponse bankBranchListResponse;

        private Builder() {
            bankBranchListResponse = new BankBranchListResponse();
        }

        public static Builder bankBranchListResponse() {
            return new Builder();
        }

        public Builder bankId(Long bankId) {
            bankBranchListResponse.setBankId(bankId);
            return this;
        }

        public Builder branchId(Long branchId) {
            bankBranchListResponse.setBranchId(branchId);
            return this;
        }

        public Builder branchCode(String branchCode) {
            bankBranchListResponse.setBranchCode(branchCode);
            return this;
        }

        public Builder branchName(String branchName) {
            bankBranchListResponse.setBranchName(branchName);
            return this;
        }

        public Builder telNumber(String telNumber) {
            bankBranchListResponse.setTelNumber(telNumber);
            return this;
        }

        public Builder faxNumber(String faxNumber) {
            bankBranchListResponse.setFaxNumber(faxNumber);
            return this;
        }

        public Builder boxCode(String boxCode) {
            bankBranchListResponse.setBoxCode(boxCode);
            return this;
        }

        public Builder postCode(String postCode) {
            bankBranchListResponse.setPostCode(postCode);
            return this;
        }

        public Builder disableDate(Date disableDate) {
            bankBranchListResponse.setDisableDate(disableDate);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            bankBranchListResponse.setActiveFlag(activeFlag);
            return this;
        }

        public Builder branchAddress(String branchAddress) {
            bankBranchListResponse.setBranchAddress(branchAddress);
            return this;
        }

        public BankBranchListResponse build() {
            return bankBranchListResponse;
        }
    }
}
