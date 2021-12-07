package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.BankAccountTypeResponse;

import java.util.List;

public interface BankAccountTypeService {
    List<BankAccountTypeResponse> getBankAccountType();
}
