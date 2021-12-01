package ir.demisco.cfs.model.dto.request;

public class ChequeBookChangeStatusRequest {
    private Long chequeBookId;
    private Boolean activeFlag;

    public Long getChequeBookId() {
        return chequeBookId;
    }

    public void setChequeBookId(Long chequeBookId) {
        this.chequeBookId = chequeBookId;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }
}
