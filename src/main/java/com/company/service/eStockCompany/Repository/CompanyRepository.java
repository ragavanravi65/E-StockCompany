package com.company.service.eStockCompany.Repository;

import com.company.service.eStockCompany.model.entity.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<CompanyDetails,String> {
}
