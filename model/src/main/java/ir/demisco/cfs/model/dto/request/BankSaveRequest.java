package ir.demisco.cfs.model.dto.request;

public class BankSaveRequest {
    private Long bankId;
    private String bankCode;
    private String bankName;
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

    public static BankSaveRequest.Builder builder() {
        return new BankSaveRequest.Builder();
    }

    public static final class Builder {
        private BankSaveRequest bankSaveRequest;

        private Builder() {
            bankSaveRequest = new BankSaveRequest();
        }

        public static Builder aBankSaveRequuest() {
            return new Builder();
        }

        public Builder bankId(Long bankId) {
            bankSaveRequest.setBankId(bankId);
            return this;
        }

        public Builder bankCode(String bankCode) {
            bankSaveRequest.setBankCode(bankCode);
            return this;
        }

        public Builder bankName(String bankName) {
            bankSaveRequest.setBankName(bankName);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            bankSaveRequest.setActiveFlag(activeFlag);
            return this;
        }

        public Builder formatNumber(String formatNumber) {
            bankSaveRequest.setFormatNumber(formatNumber);
            return this;
        }

        public Builder iconName(String iconName) {
            bankSaveRequest.setIconName(iconName);
            return this;
        }

        public BankSaveRequest build() {
            return bankSaveRequest;
        }
    }
}
