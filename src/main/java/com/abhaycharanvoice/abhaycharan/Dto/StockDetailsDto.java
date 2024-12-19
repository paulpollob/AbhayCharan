package com.abhaycharanvoice.abhaycharan.Dto;

import com.abhaycharanvoice.abhaycharan.Enum.InvoiceType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StockDetailsDto {
    private Long id;
    private String productCode;
    private String productName;
    private Integer packSize;
    private Integer previousStockQty;
    private Integer currentStockQty;
    private InvoiceType invoiceType;
    private String invoiceNo;
    private Integer damageQty;
    private Integer sellingQty;
    private String createdBy;
    private String updatedBy;
    private Integer activeFlag;
    private LocalDateTime createdAt;
    private LocalDate createdDate;
    private LocalDateTime updatedAt;
}
