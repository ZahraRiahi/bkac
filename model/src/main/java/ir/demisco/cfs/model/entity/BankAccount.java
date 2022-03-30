package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;
import ir.demisco.cloud.basic.model.entity.org.Organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "BKACBankAccount")
@Table(name = "bank_account", schema = "bkac")
public class BankAccount extends AuditModel<Long> {
    private Long id;
    private String code;
    private String description;
    private MoneyType moneyType;
    private BankAccountType bankAccountType;
    private String accountOwnerName;
    private String accountCodeSheba;
    private Bank bank;
    private FinancialAccount financialAccount;
    private BankBranch bankBranch;
    private BankAccount supportAccount;
    private CentricAccount centricAccount;
    private Organization organization;
    private Date disableDate;
    private BankAccountDepartment bankAccountDepartment;
    private Boolean internetFlag;
    private Boolean relatedFlag;
    private Boolean defaultFlag;
    private LocalDateTime deletedDate;

    @Id
    @SequenceGenerator(schema = "bkac", name = "bank_account_generator", sequenceName = "sq_bank_account", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_account_generator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MONEY_TYPE_ID")
    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ACCOUNT_TYPE_ID")
    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    @Column(name = "ACCOUNT_OWNER_NAME")
    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    @Column(name = "ACCOUNT_CODE_SHEBA")
    public String getAccountCodeSheba() {
        return accountCodeSheba;
    }

    public void setAccountCodeSheba(String accountCodeSheba) {
        this.accountCodeSheba = accountCodeSheba;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID")
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_ACCOUNT_ID")
    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_BRANCH_ID")
    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPPORT_ACCOUNT_ID")
    public BankAccount getSupportAccount() {
        return supportAccount;
    }

    public void setSupportAccount(BankAccount supportAccount) {
        this.supportAccount = supportAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CENTRIC_ACCOUNT_ID")
    public CentricAccount getCentricAccount() {
        return centricAccount;
    }

    public void setCentricAccount(CentricAccount centricAccount) {
        this.centricAccount = centricAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Column(name = "DISABLE_DATE")
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ACCOUNT_DEPARTMENT_ID")
    public BankAccountDepartment getBankAccountDepartment() {
        return bankAccountDepartment;
    }

    public void setBankAccountDepartment(BankAccountDepartment bankAccountDepartment) {
        this.bankAccountDepartment = bankAccountDepartment;
    }

    @Column(name = "INTERNET_FLAG")
    public Boolean getInternetFlag() {
        return internetFlag;
    }

    public void setInternetFlag(Boolean internetFlag) {
        this.internetFlag = internetFlag;
    }

    @Column(name = "RELATED_FLAG")
    public Boolean getRelatedFlag() {
        return relatedFlag;
    }

    public void setRelatedFlag(Boolean relatedFlag) {
        this.relatedFlag = relatedFlag;
    }

    @Column(name = "DEFAULT_FLAG")
    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }
}
