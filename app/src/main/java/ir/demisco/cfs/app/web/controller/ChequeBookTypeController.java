package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.service.api.ChequeBookTypeService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-chequeBookType")
public class ChequeBookTypeController {
private final ChequeBookTypeService chequeBookTypeService;

    public ChequeBookTypeController(ChequeBookTypeService chequeBookTypeService) {
        this.chequeBookTypeService = chequeBookTypeService;
    }

    @PostMapping("/List")
    public ResponseEntity<DataSourceResult> chequeBookTypeListResponseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(chequeBookTypeService.getListChequeBookType(dataSourceRequest));
    }

}
