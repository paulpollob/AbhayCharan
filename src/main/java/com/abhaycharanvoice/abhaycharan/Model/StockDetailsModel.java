package com.abhaycharanvoice.abhaycharan.Model;

import com.abhaycharanvoice.abhaycharan.Enum.InvoiceType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class StockDetailsModel {
    @Id
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
    private Integer activeFlag;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;
}
