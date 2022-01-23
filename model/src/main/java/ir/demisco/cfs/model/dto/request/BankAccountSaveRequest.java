package ir.demisco.cfs.model.dto.request;

import java.util.Date;

public class BankAccountSaveRequest {
    private Long bankAccountId;
    private String bankAccountCode;
    private Long bankId;
    private Long bankBranchId;
    private Long bankAccountTypeId;
    private String description;
    private Long moneyTypeId;
    private String accountOwnerName;
    private String accountCodeSheba;
    private Long financialAccountId;
    private Long centricAccountId;
    private Long organizationId;
    private Boolean activeFlag;
    private Long supportAccountId;
    private Long bankAccountDepartmentId;
    private Date disableDate;
    private Boolean internetFlag;
    private Boolean relatedFlag;
    private Boolean defaultFlag;

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

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(Long bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public Long getBankAccountTypeId() {
        return bankAccountTypeId;
    }

    public void setBankAccountTypeId(Long bankAccountTypeId) {
        this.bankAccountTypeId = bankAccountTypeId;
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

    public Long getFinancialAccountId() {
        return financialAccountId;
    }

    public void setFinancialAccountId(Long financialAccountId) {
        this.financialAccountId = financialAccountId;
    }

    public Long getCentricAccountId() {
        return centricAccountId;
    }

    public void setCentricAccountId(Long centricAccountId) {
        this.centricAccountId = centricAccountId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getSupportAccountId() {
        return supportAccountId;
    }

    public void setSupportAccountId(Long supportAccountId) {
        this.supportAccountId = supportAccountId;
    }

    public Long getBankAccountDepartmentId() {
        return bankAccountDepartmentId;
    }

    public void setBankAccountDepartmentId(Long bankAccountDepartmentId) {
        this.bankAccountDepartmentId = bankAccountDepartmentId;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    public Boolean getInternetFlag() {
        return internetFlag;
    }

    public void setInternetFlag(Boolean internetFlag) {
        this.internetFlag = internetFlag;
    }

    public Boolean getRelatedFlag() {
        return relatedFlag;
    }

    public void setRelatedFlag(Boolean relatedFlag) {
        this.relatedFlag = relatedFlag;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
}
