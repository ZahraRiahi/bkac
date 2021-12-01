package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.ChequeBookChangeStatusRequest;
import ir.demisco.cfs.model.dto.request.ChequeBookRequest;
import ir.demisco.cfs.service.api.ChequeBookService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-chequeBook")
public class ChequeBookController {
    private final ChequeBookService chequeBookService;

    public ChequeBookController(ChequeBookService chequeBookService) {
        this.chequeBookService = chequeBookService;
    }

    @PostMapping("/List")
    public ResponseEntity<DataSourceResult> chequeBookTypeListResponseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(chequeBookService.getListChequeBook(dataSourceRequest));
    }

    @GetMapping("/Delete/{id}")
    public ResponseEntity<Boolean> deleteChequeBook(@PathVariable("id") Long chequeBookId) {
        boolean result;
        result = chequeBookService.deleteChequeBook(chequeBookId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/ChangeStatus")
    public ResponseEntity<Boolean> getBankBranchChangeStatus(@RequestBody ChequeBookChangeStatusRequest chequeBookChangeStatusRequest) {
        boolean result;
        result = chequeBookService.getChequeBookChangeStatus(chequeBookChangeStatusRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/Save")
    public ResponseEntity<Boolean> saveChequeBook(@Valid @RequestBody ChequeBookRequest chequeBookRequest, Errors errors) {
        boolean result;
        result = chequeBookService.saveChequeBook(chequeBookRequest,errors);
        return ResponseEntity.ok(result);
    }
}
