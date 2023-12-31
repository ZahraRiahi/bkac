package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.BankAccountChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.BankAccountSaveRequest;
import ir.demisco.cfs.model.dto.request.BankListRequest;
import ir.demisco.cfs.model.dto.response.BankAccountOutputModelResponse;
import ir.demisco.cfs.service.api.BankAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-bankAccount")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/List")
    public ResponseEntity<DataSourceResult> bankAccountListResponseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(bankAccountService.getListBankAccount(dataSourceRequest));
    }

    @PostMapping("/Save")
    public ResponseEntity<Boolean> saveBankAccount(@RequestBody BankAccountSaveRequest bankAccountSaveRequest) {
        boolean result;
        result = bankAccountService.saveBankAccount(bankAccountSaveRequest);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/ChangeStatus")
    public ResponseEntity<Boolean> getBankAccountChangeStatus(@RequestBody BankAccountChangeStatusRequest bankAccountChangeStatusRequest) {
        boolean result;
        result = bankAccountService.getBankAccountChangeStatus(bankAccountChangeStatusRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/lovList")
    public ResponseEntity<List<BankAccountOutputModelResponse>> responseEntity(@RequestBody BankListRequest bankListRequest) {
        return ResponseEntity.ok(bankAccountService.getBankListLov(bankListRequest));
    }
}
