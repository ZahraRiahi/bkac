package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.BankAccountTypeResponse;
import ir.demisco.cfs.service.api.BankAccountTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api-bankAccountType")
public class BankAccountTypeController {
    private final BankAccountTypeService bankAccountTypeService;

    public BankAccountTypeController(BankAccountTypeService bankAccountTypeService) {
        this.bankAccountTypeService = bankAccountTypeService;
    }


    @GetMapping("/Get")
    public ResponseEntity<List<BankAccountTypeResponse>> responseEntity() {
        return ResponseEntity.ok(bankAccountTypeService.getBankAccountType());
    }
}
