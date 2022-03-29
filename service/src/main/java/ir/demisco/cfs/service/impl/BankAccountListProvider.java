package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.BankAccountListResponse;
import ir.demisco.cfs.model.entity.BankAccount;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankAccountListProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return BankAccount.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("code"),
                filterContext.getPath("description"),
                filterContext.getPath("moneyType.id"),
                filterContext.getPath("moneyType.description"),
                filterContext.getPath("bank.id"),
                filterContext.getPath("bank.name"),
                filterContext.getPath("bankBranch.id"),
                filterContext.getPath("bankBranch.name"),
                filterContext.getPath("bankAccountType.id"),
                filterContext.getPath("bankAccountType.description"),
                filterContext.getPath("financialAccount.id"),
                filterContext.getPath("financialAccount.description"),
                filterContext.getPath("disableDate"),
                filterContext.getPath("accountOwnerName"),
                filterContext.getPath("accountCodeSheba")

        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;

            return BankAccountListResponse.builder()
                    .bankAccountId(Long.parseLong(array[0].toString()))
                    .bankAccountCode(array[1].toString())
                    .description(array[2].toString())
                    .moneyTypeId(Long.parseLong(array[3].toString()))
                    .moneyTypeDescription((String) array[4])
                    .bankId((Long) array[5])
                    .bankName((String) array[6])
                    .bankBranchId((Long) array[7])
                    .branchName((String) array[8])
                    .bankAccountTypeId((Long) array[9])
                    .bankAccountTypeDescription((String) array[10])
                    .financialAccountId((Long) array[11])
                    .financialAccountDescription((String) array[12])
                    .activeFlag(false)
                    .disableDate((Date) array[13])
                    .accountOwnerName((String) array[14])
                    .accountCodeSheba((String) array[15])
                    .build();
        }).collect(Collectors.toList());
    }
    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Root<Object> root = filterContext.getRoot();
        Join<Object, Object> financialAccount = root.join("financialAccount", JoinType.LEFT);
        criteriaBuilder.equal(financialAccount.get("id"), root.get("id"));

        return null;
    }
}
