package com.company.service.eStockCompany.mapper;


import com.company.service.eStockCompany.model.DTO.Company;
import com.company.service.eStockCompany.model.DTO.Stock;
import com.company.service.eStockCompany.model.entity.CompanyDetails;
import com.company.service.eStockCompany.model.entity.StockEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleSourceDestinationMapper {
    CompanyDetails companyToDetails(Company source);
    Company DetailsToCompany(CompanyDetails destination);

    Stock EntityToStock(StockEntity stockEntity);
}