package com.abhaycharanvoice.abhaycharan.Model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductInfoModel {
    private String productCode;
    private String productName;
    private String productCategory;
    private String productSize;
    private String packSize;
    private Integer productQty;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private Integer currentStock;
    private Integer previousStock;
    private BigDecimal discount;
    private Integer activeFlag;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
