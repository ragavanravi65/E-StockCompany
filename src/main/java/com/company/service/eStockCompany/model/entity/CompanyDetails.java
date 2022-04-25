package com.company.service.eStockCompany.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@CompanyDetailConstraint
public class CompanyDetails {
    @Id
    @NotNull
    private String companyCode;
    @NotNull
    private String companyName;
    @NotNull
    private String companyCEO;
    @NotNull
    private Long companyTurnOver;
    @NotNull
    private String website;
    @NotNull
    private String stockExchange;

}

