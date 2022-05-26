package com.company.service.eStockCompany.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @NotNull(message = "company code can't be null")
    private String companyCode;
    @NotNull(message="company Name can't be null")
    private String companyName;
    @NotNull(message="company CEO can't be null")
    private String companyCEO;
    @NotNull(message="company TurnOver should be greater than 100000000")
    @Min(100000000)
    private Long companyTurnOver;
    @NotNull(message="company website can't be null")
    private String website;
    @NotNull(message="company stockExchange can't be null")
    private String stockExchange;
}
