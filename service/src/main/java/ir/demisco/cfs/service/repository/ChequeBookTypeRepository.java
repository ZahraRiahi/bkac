package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.ChequeBookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChequeBookTypeRepository extends JpaRepository<ChequeBookType, Long> {
    @Query(value = "select count(t.id)" +
            "  from bkac.cheque_Book_Type t" +
            "  left join bkac.cheque_book chb" +
            "    on t.id = chb.cheque_book_type_id" +
            "  left join bkac.cheque_print_config chpc" +
            "    on t.id = chpc.cheque_book_type_id" +
            " where t.id = :chequeBookTypeId" +
            "   and (chb.cheque_book_type_id = :chequeBookTypeId or" +
            "       chpc.cheque_book_type_id = :chequeBookTypeId)", nativeQuery = true)
    Long getCountByIdForDelete(Long chequeBookTypeId);
}
