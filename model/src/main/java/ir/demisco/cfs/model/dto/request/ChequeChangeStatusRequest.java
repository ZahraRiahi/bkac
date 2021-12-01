package ir.demisco.cfs.model.dto.request;

public class ChequeChangeStatusRequest {
    private Long chequeId;
    private Long chequeStatusId;

    public Long getChequeId() {
        return chequeId;
    }

    public void setChequeId(Long chequeId) {
        this.chequeId = chequeId;
    }

    public Long getChequeStatusId() {
        return chequeStatusId;
    }

    public void setChequeStatusId(Long chequeStatusId) {
        this.chequeStatusId = chequeStatusId;
    }
}
