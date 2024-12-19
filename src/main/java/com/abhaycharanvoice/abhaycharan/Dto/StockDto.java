package com.abhaycharanvoice.abhaycharan.Dto;

import com.abhaycharanvoice.abhaycharan.Enum.InvoiceType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockDto {
    private Long id;
    private String productCode;
    private String authorityCode;
    private String distributorCode;
    private Integer previousQty;
    private Integer currentQty ;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private InvoiceType stockEventType;
    private String stockEventId;
    private Integer activeFlag;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
