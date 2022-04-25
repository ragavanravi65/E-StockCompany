package com.company.service.eStockCompany.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StockKey.class)
public class StockEntity {
    @Id
    private String companyCode;
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false, updatable = false)
    private Date timestamp;
    @Id
    private Double stockPrice;

    @PrePersist
    private void onCreate(){
        timestamp=new Date();
    }
}
