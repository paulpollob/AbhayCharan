package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Data
@Entity
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    @PrePersist
    public void generateId() {
        this.productCode = String.format("prod-%04d", this.id);
    }
    private String productCode;
    private String productName;
    private String productSize;
    private String packSize;
    private Integer productQty;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private Integer currentStock;
    private Integer previousStock;
    private BigDecimal discount;
    private Integer activeFlag;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
