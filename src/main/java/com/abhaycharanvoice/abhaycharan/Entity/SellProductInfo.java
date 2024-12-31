package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class SellProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sellprod_seq")
    @SequenceGenerator(name = "sellprod_seq", sequenceName = "sellprod_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    private String productCode;
    private String productName;
    private String productCategory;
    private Integer sellingQty;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private Integer activeFlag;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private SellingInfo sellingInfo;
}
