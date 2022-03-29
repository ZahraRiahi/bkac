package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.BankBranchChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.BankBranchGetRequest;
import ir.demisco.cfs.model.dto.request.BankBranchRequest;
import ir.demisco.cfs.model.dto.response.BankBranchGetResponse;
import ir.demisco.cfs.service.api.BankBranchService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@RestController
@RequestMapping("/api-bankBranch")
public class BankBranchController {
    private BankBranchService bankBranchService;

    public BankBranchController(BankBranchService bankBranchService) {
        this.bankBranchService = bankBranchService;
    }

    @PostMapping("/List")
    public ResponseEntity<DataSourceResult> bankBranchListResponseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(bankBranchService.getListBankBranch(dataSourceRequest));
    }

    @PostMapping("/Get")
    public ResponseEntity<List<BankBranchGetResponse>> responseEntity(@RequestBody BankBranchGetRequest bankBranchGetRequest) {
        return ResponseEntity.ok(bankBranchService.getBankBranch(bankBranchGetRequest));
    }

    @PostMapping("/Save")
    public ResponseEntity<Boolean> saveBankBranch(@RequestBody BankBranchRequest bankBranchRequest) {
        boolean result;
        result = bankBranchService.saveBankBranch(bankBranchRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/Delete/{id}")
    public ResponseEntity<Boolean> deleteBankBranch(@PathVariable("id") Long bankBranchId) {
        boolean result;
        result = bankBranchService.deleteBankBranch(bankBranchId);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/ChangeStatus")
    public ResponseEntity<Boolean> getBankBranchChangeStatus(@RequestBody BankBranchChangeStatusRequest bankBranchChangeStatusRequest) {
        boolean result;
        result = bankBranchService.getBankBranchChangeStatus(bankBranchChangeStatusRequest);
        return ResponseEntity.ok(result);
    }
}
