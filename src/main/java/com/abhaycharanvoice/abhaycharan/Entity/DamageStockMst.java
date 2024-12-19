package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class DamageStockMst {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "damage_stock_mst_seq")
    @SequenceGenerator(name = "damage_stock_mst_seq", sequenceName = "damage_stock_mst_seq",initialValue = 10000,allocationSize = 1 )

    private Long id;
    private String invoiceNo;
    private Integer activeFlag;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDate createdDate;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "damageStockMst", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DamageStockDetail> damageStockDetails;
}
