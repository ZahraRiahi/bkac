package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.BankAccountTypeResponse;
import ir.demisco.cfs.service.api.BankAccountTypeService;
import ir.demisco.cfs.service.repository.BankAccountTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultBankAccountType implements BankAccountTypeService {
    private final BankAccountTypeRepository bankAccountTypeRepository;

    public DefaultBankAccountType(BankAccountTypeRepository bankAccountTypeRepository) {
        this.bankAccountTypeRepository = bankAccountTypeRepository;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<BankAccountTypeResponse> getBankAccountType() {
        return bankAccountTypeRepository.getBankAccountTypeList().stream().map(e -> BankAccountTypeResponse.builder()
                .bankAccountTypeId(e.getId())
                .description(e.getDescription())
                .build()).collect(Collectors.toList());
    }
}
