package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.BankBranchListResponse;
import ir.demisco.cfs.model.entity.BankBranch;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Selection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankBranchListProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return BankBranch.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("bank.id"),
                filterContext.getPath("id"),
                filterContext.getPath("code"),
                filterContext.getPath("name"),
                filterContext.getPath("telNumber"),
                filterContext.getPath("faxNumber"),
                filterContext.getPath("boxCode"),
                filterContext.getPath("postCode"),
                filterContext.getPath("disableDate"),
                filterContext.getPath("address")
        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;

            return BankBranchListResponse.builder()
                    .branchId((Long) array[1])
                    .bankId((Long) array[0])
                    .branchName((String) array[3])
                    .branchCode((String) array[2])
                    .telNumber((String) array[4])
                    .faxNumber((String) array[5])
                    .boxCode((String) array[6])
                    .postCode((String) array[7])
                    .disableDate((Date) array[8])
                    .activeFlag(false)
                    .branchAddress((String) array[9])
                    .build();
        }).collect(Collectors.toList());
    }
}
