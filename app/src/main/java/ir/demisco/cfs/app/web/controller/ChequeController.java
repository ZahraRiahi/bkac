package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.ChequeChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.ChequeStatusListRequest;
import ir.demisco.cfs.service.api.ChequeService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-cheque")
public class ChequeController {
    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @PostMapping("/List")
    public ResponseEntity<DataSourceResult> chequeBookTypeListResponseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(chequeService.getListCheque(dataSourceRequest));
    }

    @PostMapping("/Update")
    public ResponseEntity<Boolean> updateChequeChangeStatus(@RequestBody ChequeChangeStatusRequest chequeChangeStatusRequest) {
        boolean result;
        result = chequeService.updateChequeChangeStatus(chequeChangeStatusRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveCheque(@RequestBody ChequeStatusListRequest chequeStatusListRequest) {
        boolean result;
        result = chequeService.saveCheque(chequeStatusListRequest);
        return ResponseEntity.ok(result);
    }
}
