package ir.demisco.cfs.model.dto.response;

public class ChequeUseTypeListOutputResponse {
    private Long id;
    private String code;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ChequeUseTypeListOutputResponse.Builder builder() {
        return new ChequeUseTypeListOutputResponse.Builder();
    }

    public static final class Builder {
        private ChequeUseTypeListOutputResponse chequeUseTypeListOutputResponse;

        private Builder() {
            chequeUseTypeListOutputResponse = new ChequeUseTypeListOutputResponse();
        }

        public static Builder chequeUseTypeListOutputResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            chequeUseTypeListOutputResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            chequeUseTypeListOutputResponse.setCode(code);
            return this;
        }

        public Builder description(String description) {
            chequeUseTypeListOutputResponse.setDescription(description);
            return this;
        }

        public ChequeUseTypeListOutputResponse build() {
            return chequeUseTypeListOutputResponse;
        }
    }
}
