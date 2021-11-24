package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.BankBranchGetRequest;
import ir.demisco.cfs.model.dto.response.BankBranchGetResponse;
import ir.demisco.cfs.service.api.BankBranchService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
