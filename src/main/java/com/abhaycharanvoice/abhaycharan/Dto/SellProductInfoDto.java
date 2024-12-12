package com.abhaycharanvoice.abhaycharan.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SellProductInfoDto {
    private String productCode;
    private String productName;
    private Integer sellingQty;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private Integer activeFlag;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
