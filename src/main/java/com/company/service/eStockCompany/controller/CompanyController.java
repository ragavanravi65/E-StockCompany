package com.company.service.eStockCompany.controller;


import com.company.service.eStockCompany.model.DTO.Company;
import com.company.service.eStockCompany.model.DTO.CompanyWithStock;
import com.company.service.eStockCompany.model.entity.CompanyDetails;
import com.company.service.eStockCompany.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/market/company")
@Slf4j
@Validated
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @PostMapping("/register")
    public ResponseEntity<Company> registerCompanyInfo(@RequestBody @Valid Company companyDetails) {
        Company resultSet = companyService.registerCompanyService(companyDetails);
        return ResponseEntity.ok().body(resultSet);
    }


    @GetMapping("/info/{companycode}")
    public ResponseEntity<CompanyWithStock> fetchByCode(@PathVariable("companycode") @NotBlank
                                                            @Size(min = 1) String companyCode){
        if(!companyCode.isEmpty() && companyCode.length()>0) {
            CompanyWithStock companyDetailsResult = companyService.fetchByCompanyCode(companyCode);
            return ResponseEntity.ok().body(companyDetailsResult);
        }
        return null;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CompanyWithStock>> fetchAllCompanies(){
        List<CompanyWithStock> resultList= companyService.fetchAll();
        return ResponseEntity.ok().body(resultList);
    }

    @DeleteMapping("/delete/{companycode}")
    public ResponseEntity<String> deleteByCode(@PathVariable("companycode") String companyCode){
        return  ResponseEntity.ok().body(companyService.deleteById(companyCode));
    }

}
