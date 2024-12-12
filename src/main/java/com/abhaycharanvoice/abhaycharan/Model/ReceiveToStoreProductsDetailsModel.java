package com.abhaycharanvoice.abhaycharan.Model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class ReceiveToStoreProductsDetailsModel {
    private Long id;
    private String productCode;
    private String productName;
    private Integer productReceivedQty;
    private Integer productReturnQty;
    private String productSize;
    private String packSize;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private BigDecimal discount;
    private String category;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Integer activeFlag;
}
