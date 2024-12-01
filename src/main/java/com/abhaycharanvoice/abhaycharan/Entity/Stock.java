package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Stock {
    @Id
    private Long id;
    private String stockId;
    private String productCode;
    private Integer StockId;
    private Integer stockIn;
    private Integer stockOut;
    private Integer stockBalance;
    private Integer activeFlag;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
