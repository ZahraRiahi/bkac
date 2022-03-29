package ir.demisco.cfs.app.web.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import ir.demisco.cfs.model.dto.request.ChequeBookTypeRequest;
import ir.demisco.cfs.service.api.ChequeBookTypeService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/Save")
    public ResponseEntity<Boolean> saveChequeBookType(@RequestBody ChequeBookTypeRequest chequeBookTypeRequest) {
        boolean result;
        result = chequeBookTypeService.saveChequeBookType(chequeBookTypeRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/Delete/{id}")
    public ResponseEntity<Boolean> deleteChequeBookType(@PathVariable("id") Long chequeBookTypeId) {
        boolean result;
        result = chequeBookTypeService.deleteChequeBookType(chequeBookTypeId);
        return ResponseEntity.ok(result);
    }

}
