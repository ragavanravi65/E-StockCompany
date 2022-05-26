package com.company.service.eStockCompany.controller;

import com.company.service.eStockCompany.TestUtils.CommonUtils;
import com.company.service.eStockCompany.model.dto.Company;
import com.company.service.eStockCompany.model.dto.CompanyWithStock;
import com.company.service.eStockCompany.service.CompanyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTests {

    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @Test
    public void registerCompanyInfoSuccess() throws Exception {

        when(companyService.registerCompanyService(Mockito.any())).thenReturn(CommonUtils.getCompany());
        Company value=CommonUtils.getCompany();
        ResponseEntity<Company> result = companyController.registerCompanyInfo(value);
        Assert.assertNotNull(result.getBody());

    }

    @Test
    public void fetchByCodeSuccess() throws Exception {
        CompanyWithStock companyDetailsResult = CompanyWithStock.builder()
                .company(CommonUtils.getCompany()).build();
        when(companyService.fetchByCompanyCode(Mockito.any())).thenReturn(companyDetailsResult);
        Company value=CommonUtils.getCompany();
        ResponseEntity<CompanyWithStock> result = companyController.fetchByCode("123456");
        Assert.assertNotNull(result.getBody());
    }

    @Test
    public void fetchAllSuccess() throws Exception {
        CompanyWithStock companyDetailsResult = CompanyWithStock.builder()
                .company(CommonUtils.getCompany()).build();
        List<CompanyWithStock> finalList=new ArrayList<>();
        finalList.add(companyDetailsResult);
        when(companyService.fetchAll()).thenReturn(finalList);
        Company value=CommonUtils.getCompany();
        ResponseEntity<List<CompanyWithStock>>  result = companyController.fetchAllCompanies();
        Assert.assertNotNull(result.getBody());

    }

    @Test
    public void deleteByCodeSuccess() throws Exception {
        when(companyService.deleteById(Mockito.any())).thenReturn("successfully deleted the details of stock and company ");
        Company value=CommonUtils.getCompany();
        ResponseEntity<String> result = companyController.deleteByCode("12341212");
        Assert.assertEquals("successfully deleted the details of stock and company ",result.getBody());

    }
}
