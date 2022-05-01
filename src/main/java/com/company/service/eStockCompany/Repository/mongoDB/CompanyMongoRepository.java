package com.company.service.eStockCompany.Repository.mongoDB;

import com.company.service.eStockCompany.model.entity.mongoDB.CompanyCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyMongoRepository extends MongoRepository<CompanyCollection,String> {
}
