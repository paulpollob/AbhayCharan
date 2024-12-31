package com.abhaycharanvoice.abhaycharan.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DamageStockDetailModel {
    private Long id;
    private String productCode;
    private String productName;
    private String productCategory;
    private Integer damageQty;
    private Integer activeFlag;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
