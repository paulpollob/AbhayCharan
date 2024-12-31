package com.abhaycharanvoice.abhaycharan.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInfoDto {
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
