package com.company.service.eStockCompany.mapper;


import com.company.service.eStockCompany.model.DTO.Company;
import com.company.service.eStockCompany.model.entity.CompanyDetails;
import com.company.service.eStockCompany.model.entity.mongoDB.CompanyCollection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SimpleSourceDestinationMapper {
    CompanyDetails companyToDetails(Company source);
    Company DocToCompany(CompanyCollection destination);
    CompanyCollection companyToCollection(Company company);
    List<CompanyDetails> collectionToCompanyDetails(List<CompanyCollection> all);

    Company DetailsToCompany(CompanyDetails destination);
}