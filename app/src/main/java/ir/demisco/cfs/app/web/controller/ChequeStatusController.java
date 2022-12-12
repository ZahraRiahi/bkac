package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.ChequeStatusListInputRequest;
import ir.demisco.cfs.model.dto.response.ChequeUseTypeListOutputResponse;
import ir.demisco.cfs.service.api.ChequeStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-chequeStatus")
public class ChequeStatusController {
    private final ChequeStatusService chequeStatusService;

    public ChequeStatusController(ChequeStatusService chequeStatusService) {
        this.chequeStatusService = chequeStatusService;
    }

    @PostMapping("/list")
    public ResponseEntity<List<ChequeUseTypeListOutputResponse>> responseEntity(@RequestBody ChequeStatusListInputRequest chequeStatusListInputRequest) {
        return ResponseEntity.ok(chequeStatusService.getChequeStatus(chequeStatusListInputRequest));
    }
}
