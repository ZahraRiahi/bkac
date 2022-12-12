package ir.demisco.cfs.model.dto.request;

public class ChequeStatusListInputRequest {
    private Long chequeStatusId;
    private String chequeStatusCode;

    public Long getChequeStatusId() {
        return chequeStatusId;
    }

    public void setChequeStatusId(Long chequeStatusId) {
        this.chequeStatusId = chequeStatusId;
    }

    public String getChequeStatusCode() {
        return chequeStatusCode;
    }

    public void setChequeStatusCode(String chequeStatusCode) {
        this.chequeStatusCode = chequeStatusCode;
    }
}
