package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.ChequeBookListResponse;
import ir.demisco.cfs.model.entity.ChequeBook;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Selection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChequeBookProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return ChequeBook.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("numStart"),
                filterContext.getPath("numEnd"),
                filterContext.getPath("flagRemit"),
                filterContext.getPath("disableDate"),
                filterContext.getPath("bankAccount.id"),
                filterContext.getPath("chequeBookType.id"),
                filterContext.getPath("chequeBookType.description"),
                filterContext.getPath("chequeBookType.chequeCount")
        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;
            return ChequeBookListResponse.builder()
                    .chequeBookId((Long) array[0])
                    .numStart((Long) array[1])
                    .numEnd((Long) array[2])
                    .flagRemit((Boolean) array[3])
                    .disableDate((Date) array[4])
                    .bankAccountId((Long) array[5])
                    .chequeBookTypeId((Long) array[6])
                    .chequeBookTypeDescription((String) array[7])
                    .activeFlag(false)
                    .chequeCount((Long) array[8])
                    .build();
        }).collect(Collectors.toList());
    }
}
