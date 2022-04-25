package com.company.service.eStockCompany.Repository;

import com.company.service.eStockCompany.model.DTO.Stock;
import com.company.service.eStockCompany.model.entity.StockEntity;
import com.company.service.eStockCompany.model.entity.StockKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, StockKey> {

    @Query(value="SELECT * from stock_entity s WHERE s.company_code=:companyCD ORDER BY s.timestamp DESC LIMIT 1",
            nativeQuery = true)
    StockEntity findLatestByCompanyCode(@Param("companyCD") String companyCode);

    void deleteAllByCompanyCode(String companyCode);

    StockEntity findByCompanyCode(String companyCode);
}
