package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.BankListResponse;
import ir.demisco.cfs.model.entity.Bank;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankListProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return Bank.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("code"),
                filterContext.getPath("name"),
                filterContext.getPath("disableDate"),
                filterContext.getPath("formatNumber"),
                filterContext.getPath("iconName")
        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;

            return BankListResponse.builder()
                    .bankId((Long) array[0])
                    .bankCode((String) array[1])
                    .bankName((String) array[2])
                    .disableDate((Date) array[3])
                    .activeFlag(false)
                    .formatNumber((String) array[4])
                    .iconName((String) array[5])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        for (DataSourceRequest.FilterDescriptor filter : dataSourceRequest.getFilter().getFilters()) {
            switch (filter.getField()) {
                case "id":
                    if (filter.getValue() == null) {
                        filter.setDisable(true);
                    }
                    break;
            }
        }
        return null;
    }
}
