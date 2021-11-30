package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.service.api.ChequeBookService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
