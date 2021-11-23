package ir.demisco.cfs.model.dto.request;

public class BankSaveRequuest {
    private Long bankId;
    private String bankCode;
    private String bankName;
    private Long activeFlag;
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

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Long activeFlag) {
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

    public static BankSaveRequuest.Builder builder() {
        return new BankSaveRequuest.Builder();
    }

    public static final class Builder {
        private BankSaveRequuest bankSaveRequuest;

        private Builder() {
            bankSaveRequuest = new BankSaveRequuest();
        }

        public static Builder aBankSaveRequuest() {
            return new Builder();
        }

        public Builder bankId(Long bankId) {
            bankSaveRequuest.setBankId(bankId);
            return this;
        }

        public Builder bankCode(String bankCode) {
            bankSaveRequuest.setBankCode(bankCode);
            return this;
        }

        public Builder bankName(String bankName) {
            bankSaveRequuest.setBankName(bankName);
            return this;
        }

        public Builder activeFlag(Long activeFlag) {
            bankSaveRequuest.setActiveFlag(activeFlag);
            return this;
        }

        public Builder formatNumber(String formatNumber) {
            bankSaveRequuest.setFormatNumber(formatNumber);
            return this;
        }

        public Builder iconName(String iconName) {
            bankSaveRequuest.setIconName(iconName);
            return this;
        }

        public BankSaveRequuest build() {
            return bankSaveRequuest;
        }
    }
}
