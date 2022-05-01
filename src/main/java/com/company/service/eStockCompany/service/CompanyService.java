package com.company.service.eStockCompany.service;

import com.company.service.eStockCompany.model.DTO.Company;
import com.company.service.eStockCompany.model.DTO.CompanyWithStock;

import java.util.List;

public interface CompanyService {


    Company registerCompanyService(Company companyDetails);

    CompanyWithStock fetchByCompanyCode(String companyCode);

    List<CompanyWithStock> fetchAll();

    String deleteById(String companyCode);
}
