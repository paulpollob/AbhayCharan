package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Data
@Entity
public class ReceiveToStoreReceivedProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "receive_to_store_prd_seq")
    @SequenceGenerator(name = "receive_to_store_prd_seq", sequenceName = "receive_to_store_prd_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    private String productCode;
    private String productName;
    private String productCategory;
    private Integer productReceivedQty;
    private Integer productReturnedQty;
    private String productSize;
    private String packSize;
    private String productType;
    private BigDecimal tradePrice;
    private BigDecimal mrp;
    private BigDecimal discount;
    private Integer currentStock;
    private Integer previousStock;
    private Integer activeFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @ManyToOne
    private ReceiveToStoreMst receiveMst;
}
