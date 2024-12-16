package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class DamageStockMst {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "damage_seq")
    @SequenceGenerator(name = "damage_seq", sequenceName = "damage_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    private String productCode;
    private String productName;
    private Integer damageQty;
    private Integer activeFlag;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
