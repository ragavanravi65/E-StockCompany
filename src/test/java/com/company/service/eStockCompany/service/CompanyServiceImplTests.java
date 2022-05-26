package com.company.service.eStockCompany.service;

import com.company.service.eStockCompany.repository.CompanyRepository;
import com.company.service.eStockCompany.repository.mongoDB.CompanyMongoRepository;
import com.company.service.eStockCompany.TestUtils.CommonUtils;
import com.company.service.eStockCompany.model.dto.Company;
import com.company.service.eStockCompany.model.dto.CompanyStockResponse;
import com.company.service.eStockCompany.model.dto.CompanyWithStock;
import com.company.service.eStockCompany.model.entity.mongoDB.CompanyCollection;
import com.company.service.eStockCompany.service.impl.CompanyServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceImplTests {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyMongoRepository companyMongoRepository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    CompanyServiceImpl companyService;



    @Test
    public void registerCompanyServiceSuccessfully(){
        Company company= CommonUtils.getCompany();

        when(companyRepository.save(Mockito.any())).thenReturn(CommonUtils.buildCompanyDetail(company));
        when(companyMongoRepository.save(Mockito.any())).thenReturn(null);

        Company result = companyService.registerCompanyService(company);
        Assert.assertEquals(company.getCompanyCode(),result.getCompanyCode());
    }

    @Test
    public void fetchAllSuccessfully(){
        List<CompanyCollection> findByCodeResult=new ArrayList<>();
        findByCodeResult.add(CommonUtils.buildCompanyCollection());

        when(companyMongoRepository.findAll()).thenReturn(findByCodeResult);
        when(restTemplate.exchange( Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                (Class<CompanyStockResponse>) Mockito.any())).thenReturn(ResponseEntity.ok(null));

        List<CompanyWithStock> result = companyService.fetchAll();
        Assert.assertNotNull(result);
    }

}
