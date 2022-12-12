package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.ChequeUseTypeListInputRequest;
import ir.demisco.cfs.model.dto.response.ChequeUseTypeListOutputResponse;
import ir.demisco.cfs.service.api.ChequeUseTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-chequeUse")
public class ChequeUseController {
    private final ChequeUseTypeService chequeUseTypeService;

    public ChequeUseController(ChequeUseTypeService chequeUseTypeService) {
        this.chequeUseTypeService = chequeUseTypeService;
    }

    @PostMapping("/list")
    public ResponseEntity<List<ChequeUseTypeListOutputResponse>> responseEntity(@RequestBody ChequeUseTypeListInputRequest chequeUseTypeListInputRequest) {
        return ResponseEntity.ok(chequeUseTypeService.getChequeUseType(chequeUseTypeListInputRequest));
    }
}
