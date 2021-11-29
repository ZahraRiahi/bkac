package ir.demisco.cfs.model.dto.response;

import java.util.Date;

public class BankListResponse {
    private Long bankId;
    private String bankCode;
    private String bankName;
    private Date disableDate;
    private Boolean activeFlag;
    private String formatNumber;
    private String iconName;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getFormatNumber() {
        return formatNumber;
    }

    public void setFormatNumber(String formatNumber) {
        this.formatNumber = formatNumber;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public static BankListResponse.Builder builder() {
        return new BankListResponse.Builder();
    }

    public static final class Builder {
        private BankListResponse bankListResponse;

        private Builder() {
            bankListResponse = new BankListResponse();
        }

        public static Builder aBankListResponse() {
            return new Builder();
        }

        public Builder bankId(Long bankId) {
            bankListResponse.setBankId(bankId);
            return this;
        }

        public Builder bankCode(String bankCode) {
            bankListResponse.setBankCode(bankCode);
            return this;
        }

        public Builder bankName(String bankName) {
            bankListResponse.setBankName(bankName);
            return this;
        }

        public Builder disableDate(Date disableDate) {
            bankListResponse.setDisableDate(disableDate);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            bankListResponse.setActiveFlag(activeFlag);
            return this;
        }

        public Builder formatNumber(String formatNumber) {
            bankListResponse.setFormatNumber(formatNumber);
            return this;
        }

        public Builder iconName(String iconName) {
            bankListResponse.setIconName(iconName);
            return this;
        }

        public BankListResponse build() {
            return bankListResponse;
        }
    }
}
