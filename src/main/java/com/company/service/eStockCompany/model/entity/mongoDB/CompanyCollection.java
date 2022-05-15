package com.company.service.eStockCompany.model.entity.mongoDB;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="companyDetails")
public class CompanyCollection {

    @Id
    private String id;
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
