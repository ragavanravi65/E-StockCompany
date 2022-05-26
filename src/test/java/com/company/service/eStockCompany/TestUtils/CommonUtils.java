package com.company.service.eStockCompany.TestUtils;

import com.company.service.eStockCompany.model.dto.Company;
import com.company.service.eStockCompany.model.entity.CompanyDetails;
import com.company.service.eStockCompany.model.entity.mongoDB.CompanyCollection;

public class CommonUtils {

    public static CompanyDetails buildCompanyDetail(Company company) {
        return CompanyDetails.builder().companyCode("6633634358").build();
    }

    public static Company getCompany(){
        return Company.builder()
                .companyCEO("Kiele")
                .companyCode("6633634358")
                .companyName("Deckow-Bosco")
                .companyTurnOver(Long.valueOf(100000000))
                .stockExchange("NASDAQ")
                .website("123-reg.co.uk")
                .build();
    }

    public static CompanyCollection buildCompanyCollection() {
        return CompanyCollection.builder()
                .companyCEO("Kiele")
                .companyCode("6633634358")
                .companyName("Deckow-Bosco")
                .companyTurnOver(Long.valueOf(100000000))
                .stockExchange("NASDAQ")
                .website("123-reg.co.uk")
                .build();
    }
}
