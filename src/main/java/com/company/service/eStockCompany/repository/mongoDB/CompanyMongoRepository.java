package com.company.service.eStockCompany.repository.mongoDB;

import com.company.service.eStockCompany.model.entity.mongoDB.CompanyCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CompanyMongoRepository extends MongoRepository<CompanyCollection,String> {
    Optional<CompanyCollection> findByCompanyCode(String companyCode);
}
