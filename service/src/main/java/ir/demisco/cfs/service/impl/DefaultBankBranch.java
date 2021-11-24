package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.BankBranchGetRequest;
import ir.demisco.cfs.model.dto.response.BankBranchGetResponse;
import ir.demisco.cfs.model.dto.response.BankBranchListResponse;
import ir.demisco.cfs.service.api.BankBranchService;
import ir.demisco.cfs.service.repository.BankBranchRepository;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultBankBranch implements BankBranchService {
    private final GridFilterService gridFilterService;
    private final BankBranchListProvider bankBranchListProvider;
    private BankBranchRepository bankBranchRepository;

    public DefaultBankBranch(GridFilterService gridFilterService, BankBranchListProvider bankBranchListProvider, BankBranchRepository bankBranchRepository) {
        this.gridFilterService = gridFilterService;
        this.bankBranchListProvider = bankBranchListProvider;
        this.bankBranchRepository = bankBranchRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getListBankBranch(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        DataSourceResult dataSourceResult = gridFilterService.filter(dataSourceRequest, bankBranchListProvider);
        List<BankBranchListResponse> data = (List<BankBranchListResponse>) dataSourceResult.getData();
        List<BankBranchListResponse> bankBranchListResponses = new ArrayList<>();
        for (BankBranchListResponse bankBranchListResponse : data) {
            if (bankBranchListResponse.getDisableDate() == null) bankBranchListResponse.setActiveFlag(1L);
            bankBranchListResponses.add(bankBranchListResponse);
        }
        dataSourceResult.setData(bankBranchListResponses);
        return dataSourceResult;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<BankBranchGetResponse> getBankBranch(BankBranchGetRequest bankBranchGetRequest) {
        List<Object[]> bankBranchListObject = bankBranchRepository.findByBankBranchByBankId(bankBranchGetRequest.getBankId());

        return bankBranchListObject.stream().map(objects -> BankBranchGetResponse.builder().bankId(Long.parseLong(objects[1].toString()))
                .branchId(Long.parseLong(objects[0].toString()))
                .branchCode(objects[3].toString())
                .branchName(objects[2].toString())
                .build()).collect(Collectors.toList());
    }

}

