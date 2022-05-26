package com.company.service.eStockCompany.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "companyDetails",name = "CompanyData")
public class CompanyDetails {
    @Id
    private String companyCode;
    private String companyName;
    private String companyCEO;
    private Long companyTurnOver;
    private String website;
    private String stockExchange;

}

