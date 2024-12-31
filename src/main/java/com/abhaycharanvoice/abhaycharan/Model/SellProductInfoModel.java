package com.abhaycharanvoice.abhaycharan.Model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SellProductInfoModel {
    private String productCode;
    private String productName;
    private String productCategory;
    private Integer sellingQty;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private Integer activeFlag;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
