package com.company.service.eStockCompany.repository;

import com.company.service.eStockCompany.model.entity.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<CompanyDetails,String> {
    boolean findByCompanyCode(String companyCode);
}
