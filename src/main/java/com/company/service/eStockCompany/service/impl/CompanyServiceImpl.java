package com.company.service.eStockCompany.service.impl;

import com.company.service.eStockCompany.repository.CompanyRepository;
import com.company.service.eStockCompany.repository.mongoDB.CompanyMongoRepository;
import com.company.service.eStockCompany.mapper.SimpleSourceDestinationMapper;
import com.company.service.eStockCompany.model.dto.Company;
import com.company.service.eStockCompany.model.dto.CompanyStockResponse;
import com.company.service.eStockCompany.model.dto.CompanyWithStock;
import com.company.service.eStockCompany.model.dto.Stock;
import com.company.service.eStockCompany.model.entity.CompanyDetails;
import com.company.service.eStockCompany.model.entity.mongoDB.CompanyCollection;
import com.company.service.eStockCompany.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyMongoRepository companyMongoRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MongoTemplate mongoTemplate;

    private static final String STOCK_URL = "http://localhost:8082/stockData/";

    private SimpleSourceDestinationMapper mapperImpl
            = Mappers.getMapper(SimpleSourceDestinationMapper.class);

    @Override
    public Company registerCompanyService(Company company) {
        log.info("Registration of new Company details with Stock Information");
        CompanyDetails postUpdateValue = companyRepository.save(mapperImpl.companyToDetails(company));
        companyMongoRepository.save(mapperImpl.companyToCollection(company));
        return mapperImpl.detailsToCompany(postUpdateValue);
    }

    @Override
    public CompanyWithStock fetchByCompanyCode(String companyCode) {
        log.info("Fetch company Details from company code {}", companyCode);
        Optional<CompanyCollection> findByCodeResult = companyMongoRepository.findByCompanyCode(companyCode);
        CompanyCollection companyCollection = findByCodeResult.isPresent() ? findByCodeResult.get() : null;
        ResponseEntity<CompanyStockResponse> stockData = externalCallToStockService(companyCode,"/getLatest",
                HttpMethod.GET);
        return CompanyWithStock.builder().company(mapperImpl.docToCompany(companyCollection))
                .stock(stockData.getBody()!=null? stockData.getBody().getStocks().get(0):null).build();
    }


    @Override
    public List<CompanyWithStock> fetchAll() {
        List<CompanyWithStock> companyStockList = new ArrayList<>();
        List<CompanyDetails> findByCodeResult = mapperImpl.collectionToCompanyDetails(companyMongoRepository.findAll());

        findByCodeResult.stream().forEach(
                companyDet ->
                {
                    ResponseEntity<CompanyStockResponse> stockData = externalCallToStockService(companyDet.getCompanyCode(),"/getLatest",
                            HttpMethod.GET);
                    Stock resultStock = stockData!=null?
                            stockData.getBody() != null ?
                            stockData.getBody().getStocks() != null ? stockData.getBody().getStocks().get(0) : null
                                                : null
                            :null;
                    log.info("ResponseEntity" +stockData);
                    companyStockList.add(CompanyWithStock.builder()
                            .company(mapperImpl.detailsToCompany(companyDet))
                            .stock(resultStock)
                            .build());
                }
        );
        return companyStockList;
    }

    @Override
    @Transactional
    public String deleteById(String companyCode) {
        int deleteFlag = 0;
        if(companyRepository.findById(companyCode).isPresent()) {
            companyRepository.deleteById(companyCode);
            deleteFlag+=2;
        }
        mongoTemplate.findAllAndRemove(new Query(Criteria.where("companyCode").is(companyCode)), CompanyCollection.class);

        ResponseEntity<CompanyStockResponse> deletedValues= externalCallToStockService(companyCode,"/deleteAll",HttpMethod.DELETE);

        if(!deletedValues.getBody().getStocks().isEmpty())deleteFlag+=3;

        return deleteFlag == 5 ? "successfully deleted the details of stock and company " + companyCode :
                deleteFlag == 2 ? "Removed from Company Database" :
                        deleteFlag == 3 ? "Removed from Stock Database" : "No data available to delete";

    }


    private ResponseEntity<CompanyStockResponse> externalCallToStockService(String companyCode, String endpoint, HttpMethod value) {
        URI url= URI.create(STOCK_URL+"api/v1.0/market/stock"+endpoint);
        HttpHeaders headers = new HttpHeaders();
        headers.set("companyCode",companyCode);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, value,httpEntity,CompanyStockResponse.class);
    }
}