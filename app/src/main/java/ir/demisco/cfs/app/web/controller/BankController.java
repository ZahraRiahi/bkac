package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.BankSaveRequest;
import ir.demisco.cfs.service.api.BankService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/List")
    public ResponseEntity<DataSourceResult> bankListResponseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(bankService.getListBank(dataSourceRequest));
    }

    @PostMapping("/Save")
    public ResponseEntity<Boolean> saveBank(@RequestBody BankSaveRequest bankSaveRequest) {
        boolean result;
        result = bankService.saveBank(bankSaveRequest);
        return ResponseEntity.ok(result);
    }
}
