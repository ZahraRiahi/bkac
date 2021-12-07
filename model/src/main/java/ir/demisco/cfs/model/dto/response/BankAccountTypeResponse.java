package ir.demisco.cfs.model.dto.response;

public class BankAccountTypeResponse {
    private Long bankAccountTypeId;
    private String description;

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

    public static BankAccountTypeResponse.Builder builder() {
        return new BankAccountTypeResponse.Builder();
    }

    public static final class Builder {
        private BankAccountTypeResponse bankAccountTypeResponse;

        private Builder() {
            bankAccountTypeResponse = new BankAccountTypeResponse();
        }

        public static Builder aBankAccountTypeResponse() {
            return new Builder();
        }

        public Builder bankAccountTypeId(Long bankAccountTypeId) {
            bankAccountTypeResponse.setBankAccountTypeId(bankAccountTypeId);
            return this;
        }

        public Builder description(String description) {
            bankAccountTypeResponse.setDescription(description);
            return this;
        }

        public BankAccountTypeResponse build() {
            return bankAccountTypeResponse;
        }
    }
}
