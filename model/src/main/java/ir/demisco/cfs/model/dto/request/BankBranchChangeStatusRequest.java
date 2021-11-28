package ir.demisco.cfs.model.dto.request;

public class BankBranchChangeStatusRequest {
    private Long branchId;
    private Boolean activeFlag;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }
}
