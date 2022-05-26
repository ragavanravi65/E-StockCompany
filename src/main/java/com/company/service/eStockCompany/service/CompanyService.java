package com.company.service.eStockCompany.service;

import com.company.service.eStockCompany.model.dto.Company;
import com.company.service.eStockCompany.model.dto.CompanyWithStock;

import java.util.List;

public interface CompanyService {


    Company registerCompanyService(Company companyDetails);

    CompanyWithStock fetchByCompanyCode(String companyCode);

    List<CompanyWithStock> fetchAll();

    String deleteById(String companyCode);
}
