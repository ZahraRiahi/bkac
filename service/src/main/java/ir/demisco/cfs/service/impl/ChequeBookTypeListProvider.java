package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.ChequeBookTypeListResponse;
import ir.demisco.cfs.model.entity.ChequeBookType;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChequeBookTypeListProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return ChequeBookType.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("description"),
                filterContext.getPath("flagRemit"),
                filterContext.getPath("disableDate"),
                filterContext.getPath("subTitle"),
                filterContext.getPath("chequeCount")

        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;
            return ChequeBookTypeListResponse.builder()
                    .chequeBookTypeId((Long) array[0])
                    .description((String) array[1])
                    .flagRemit((Boolean) array[2])
                    .disableDate((Date) array[3])
                    .activeFlag(false)
                    .subTitle((String) array[4])
                    .chequeCount((Long) array[5])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        for (DataSourceRequest.FilterDescriptor filter : dataSourceRequest.getFilter().getFilters()) {
            switch (filter.getField()) {
                case "bank.id":
                    if (filter.getValue() == null) {
                        filter.setDisable(true);
                    }
                    break;
                default:
            }
        }
        return null;
    }
}
