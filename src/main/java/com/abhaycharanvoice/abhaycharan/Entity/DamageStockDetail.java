package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Data
@Getter
@Setter
@Entity
public class DamageStockDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "damage_detail_seq")
    @SequenceGenerator(name = "damage_detail_seq", sequenceName = "damage_detail_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    private String invoiceNo;
    private String productCode;
    private String productName;
    private Integer damageQty;
    private Integer activeFlag;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDate createdDate;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "damage_stock_id")
    private DamageStockMst damageStockMst;
}
