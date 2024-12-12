package com.abhaycharanvoice.abhaycharan.Entity;

import com.abhaycharanvoice.abhaycharan.Enum.InvoiceType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "stock_seq")
    @SequenceGenerator(name = "stock_seq", sequenceName = "stock_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    private String productCode;
    private String authorityCode;
    private String distributorCode;
    private Integer previousQty = 0;
    private Integer currentQty = 0;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private InvoiceType stockEventType;
    private String stockEventId;
    private Integer activeFlag;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}