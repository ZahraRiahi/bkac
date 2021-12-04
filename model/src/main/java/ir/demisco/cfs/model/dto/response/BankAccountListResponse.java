package ir.demisco.cfs.model.dto.response;

import java.util.Date;

public class BankAccountListResponse {
    private Long bankAccountId;
    private String bankAccountCode;
    private String description;
    private Long moneyTypeId;
    private String moneyTypeDescription;
    private Long bankId;
    private String bankName;
    private Long bankBranchId;
    private String branchName;
    private Long bankAccountTypeId;
    private String bankAccountTypeDescription;
    private Long financialAccountId;
    private String financialAccountDescription;
    private Date disableDate;
    private Boolean activeFlag;
    private String accountOwnerName;
    private String accountCodeSheba;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMoneyTypeId() {
        return moneyTypeId;
    }

    public void setMoneyTypeId(Long moneyTypeId) {
        this.moneyTypeId = moneyTypeId;
    }

    public String getMoneyTypeDescription() {
        return moneyTypeDescription;
    }

    public void setMoneyTypeDescription(String moneyTypeDescription) {
        this.moneyTypeDescription = moneyTypeDescription;
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

    public Long getBankAccountTypeId() {
        return bankAccountTypeId;
    }

    public void setBankAccountTypeId(Long bankAccountTypeId) {
        this.bankAccountTypeId = bankAccountTypeId;
    }

    public String getBankAccountTypeDescription() {
        return bankAccountTypeDescription;
    }

    public void setBankAccountTypeDescription(String bankAccountTypeDescription) {
        this.bankAccountTypeDescription = bankAccountTypeDescription;
    }

    public Long getFinancialAccountId() {
        return financialAccountId;
    }

    public void setFinancialAccountId(Long financialAccountId) {
        this.financialAccountId = financialAccountId;
    }

    public String getFinancialAccountDescription() {
        return financialAccountDescription;
    }

    public void setFinancialAccountDescription(String financialAccountDescription) {
        this.financialAccountDescription = financialAccountDescription;
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

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    public String getAccountCodeSheba() {
        return accountCodeSheba;
    }

    public void setAccountCodeSheba(String accountCodeSheba) {
        this.accountCodeSheba = accountCodeSheba;
    }

    public static BankAccountListResponse.Builder builder() {
        return new BankAccountListResponse.Builder();
    }

    public static final class Builder {
        private BankAccountListResponse bankAccountListResponse;

        private Builder() {
            bankAccountListResponse = new BankAccountListResponse();
        }

        public static Builder bankAccountListResponse() {
            return new Builder();
        }

        public Builder bankAccountId(Long bankAccountId) {
            bankAccountListResponse.setBankAccountId(bankAccountId);
            return this;
        }

        public Builder bankAccountCode(String bankAccountCode) {
            bankAccountListResponse.setBankAccountCode(bankAccountCode);
            return this;
        }

        public Builder description(String description) {
            bankAccountListResponse.setDescription(description);
            return this;
        }

        public Builder moneyTypeId(Long moneyTypeId) {
            bankAccountListResponse.setMoneyTypeId(moneyTypeId);
            return this;
        }

        public Builder moneyTypeDescription(String moneyTypeDescription) {
            bankAccountListResponse.setMoneyTypeDescription(moneyTypeDescription);
            return this;
        }

        public Builder bankId(Long bankId) {
            bankAccountListResponse.setBankId(bankId);
            return this;
        }

        public Builder bankName(String bankName) {
            bankAccountListResponse.setBankName(bankName);
            return this;
        }

        public Builder bankBranchId(Long bankBranchId) {
            bankAccountListResponse.setBankBranchId(bankBranchId);
            return this;
        }

        public Builder branchName(String branchName) {
            bankAccountListResponse.setBranchName(branchName);
            return this;
        }

        public Builder bankAccountTypeId(Long bankAccountTypeId) {
            bankAccountListResponse.setBankAccountTypeId(bankAccountTypeId);
            return this;
        }

        public Builder bankAccountTypeDescription(String bankAccountTypeDescription) {
            bankAccountListResponse.setBankAccountTypeDescription(bankAccountTypeDescription);
            return this;
        }

        public Builder financialAccountId(Long financialAccountId) {
            bankAccountListResponse.setFinancialAccountId(financialAccountId);
            return this;
        }

        public Builder financialAccountDescription(String financialAccountDescription) {
            bankAccountListResponse.setFinancialAccountDescription(financialAccountDescription);
            return this;
        }

        public Builder disableDate(Date disableDate) {
            bankAccountListResponse.setDisableDate(disableDate);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            bankAccountListResponse.setActiveFlag(activeFlag);
            return this;
        }
        public Builder accountOwnerName(String accountOwnerName) {
            bankAccountListResponse.setAccountOwnerName(accountOwnerName);
            return this;
        }
        public Builder accountCodeSheba(String accountCodeSheba) {
            bankAccountListResponse.setAccountCodeSheba(accountCodeSheba);
            return this;
        }
        public BankAccountListResponse build() {
            return bankAccountListResponse;
        }
    }
}
