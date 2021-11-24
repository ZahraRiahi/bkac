package ir.demisco.cfs.model.dto.response;

public class BankBranchGetResponse {
    private Long bankId;
    private Long branchId;
    private String branchCode;
    private String branchName;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public static BankBranchGetResponse.Builder builder() {
        return new BankBranchGetResponse.Builder();
    }

    public static final class Builder {
        private BankBranchGetResponse bankBranchGetResponse;

        private Builder() {
            bankBranchGetResponse = new BankBranchGetResponse();
        }

        public static Builder bankBranchGetResponse() {
            return new Builder();
        }

        public Builder bankId(Long bankId) {
            bankBranchGetResponse.setBankId(bankId);
            return this;
        }

        public Builder branchId(Long branchId) {
            bankBranchGetResponse.setBranchId(branchId);
            return this;
        }

        public Builder branchCode(String branchCode) {
            bankBranchGetResponse.setBranchCode(branchCode);
            return this;
        }

        public Builder branchName(String branchName) {
            bankBranchGetResponse.setBranchName(branchName);
            return this;
        }

        public BankBranchGetResponse build() {
            return bankBranchGetResponse;
        }
    }
}
