package com.abhaycharanvoice.abhaycharan.Dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReceiveToStoreReceivedProductsDto {
    private String productCode;
    private String productName;
    private Integer productReceivedQty;
    private Integer productReturnedQty;
    private String productSize;
    private String packSize;
    private String productType;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private BigDecimal discount;
    private Integer currentStock;
    private Integer previousStock;
    private Integer activeFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
