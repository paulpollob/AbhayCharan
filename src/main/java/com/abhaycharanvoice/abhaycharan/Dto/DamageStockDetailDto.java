package com.abhaycharanvoice.abhaycharan.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DamageStockDto {
    private Long id;
    private String productCode;
    private String productName;
    private Integer damageQty;
    private Integer activeFlag;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
