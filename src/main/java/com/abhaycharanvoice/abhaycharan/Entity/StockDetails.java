package com.abhaycharanvoice.abhaycharan.Entity;

import com.abhaycharanvoice.abhaycharan.Enum.InvoiceType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class StockDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "stock_detail_seq")
    @SequenceGenerator(name = "stock_detail_seq", sequenceName = "stock_detail_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    private String productCode;
    private String productName;
    private String productCategory;
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
    private LocalDateTime createdTime;
    private LocalDate createdDate;
    private LocalDateTime updatedTime;
}
