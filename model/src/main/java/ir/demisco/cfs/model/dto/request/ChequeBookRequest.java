package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

public class ChequeBookRequest {
    private Long chequeBookId;
    private Long numStart;
    private Long numEnd;
    private Boolean flagRemit;
    private Date disableDate;
    private Long bankAccountId;
    private Long chequeBookTypeId;
    private LocalDateTime chequeBookDate;

    public Long getChequeBookId() {
        return chequeBookId;
    }

    public void setChequeBookId(Long chequeBookId) {
        this.chequeBookId = chequeBookId;
    }

    @NotNull(message = "لطفا اولین شماره را وارد نمایید")
    public Long getNumStart() {
        return numStart;
    }

    public void setNumStart(Long numStart) {
        this.numStart = numStart;
    }

    @NotNull(message = "لطفا آخرین شماره را وارد نمایید.")
    public Long getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(Long numEnd) {
        this.numEnd = numEnd;
    }

    @NotNull(message = "لطفا فیلد خط خوردن حواله کرد را وارد نمایید.")
    public Boolean getFlagRemit() {
        return flagRemit;
    }

    public void setFlagRemit(Boolean flagRemit) {
        this.flagRemit = flagRemit;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    @NotNull(message = "لطفا شناسه ی حساب بانکی را وارد نمایید.")
    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    @NotNull(message = "لطفا شناسه ی نوع دسته چک را وارد نمایید.")
    public Long getChequeBookTypeId() {
        return chequeBookTypeId;
    }

    public void setChequeBookTypeId(Long chequeBookTypeId) {
        this.chequeBookTypeId = chequeBookTypeId;
    }

    @NotNull(message = "لطفا تاریخ دریافت را وارد نمایید.")
    public LocalDateTime getChequeBookDate() {
        return chequeBookDate;
    }

    public void setChequeBookDate(LocalDateTime chequeBookDate) {
        this.chequeBookDate = chequeBookDate;
    }
}
