package com.abhaycharanvoice.abhaycharan.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DamageStockDetailDto {
    private Long id;
    private String invoiceNo;
    private String productCode;
    private String productName;
    private String productCategory;
    private Integer damageQty;
    private Integer activeFlag;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDate createdDate;
    private LocalDateTime updatedAt;
}
