package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.ChequeListResponse;
import ir.demisco.cfs.model.entity.Cheque;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Selection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChequeListProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return Cheque.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("chequeNumber"),
                filterContext.getPath("useDate"),
                filterContext.getPath("description"),
                filterContext.getPath("chequeUseType.id"),
                filterContext.getPath("chequeUseType.description"),
                filterContext.getPath("nationalCode"),
                filterContext.getPath("amount"),
                filterContext.getPath("flagRemit"),
                filterContext.getPath("chequeBook.id"),
                filterContext.getPath("chequeStatus.id"),
                filterContext.getPath("chequeStatus.description")
        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;
            return ChequeListResponse.builder()
                    .chequeId((Long) array[0])
                    .chequeNumber((Long) array[1])
                    .useDate((LocalDateTime) array[2])
                    .description((String) array[3])
                    .ChequeUseTypeId((Long) array[4])
                    .ChequeUseTypeDescription((String) array[5])
                    .nationalCode((String) array[6])
                    .amount((Double) array[7])
                    .flagRemit((Boolean) array[8])
                    .chequeBookId((Long) array[9])
                    .chequeStatusId((Long) array[10])
                    .chequeStatusDescription((String) array[11])
                    .build();
        }).collect(Collectors.toList());
    }

}
