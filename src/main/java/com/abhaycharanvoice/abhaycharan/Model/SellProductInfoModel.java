package com.abhaycharanvoice.abhaycharan.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SellProductInfoModel {
    private String productCode;
    private String productName;
    private Integer sellingQty;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private Integer activeFlag;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
