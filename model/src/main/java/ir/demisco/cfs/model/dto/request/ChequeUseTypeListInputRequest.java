package ir.demisco.cfs.model.dto.request;

public class ChequeUseTypeListInputRequest {
    private Long chequeUseTypeId;
    private String chequeUseTypeCode;

    public Long getChequeUseTypeId() {
        return chequeUseTypeId;
    }

    public void setChequeUseTypeId(Long chequeUseTypeId) {
        this.chequeUseTypeId = chequeUseTypeId;
    }

    public String getChequeUseTypeCode() {
        return chequeUseTypeCode;
    }

    public void setChequeUseTypeCode(String chequeUseTypeCode) {
        this.chequeUseTypeCode = chequeUseTypeCode;
    }
}
