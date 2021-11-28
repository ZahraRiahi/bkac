package ir.demisco.cfs.model.dto.request;

public class ChequeBookTypeRequest {
    private Long chequeBookTypeId;
    private String description;
    private Boolean flagRemit;
    private Boolean activeFlag;
    private Long bankId;
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

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
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
}
