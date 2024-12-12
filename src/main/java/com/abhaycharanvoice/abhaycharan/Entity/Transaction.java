package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tran_seq")
    @SequenceGenerator(name = "tran_seq", sequenceName = "tran_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    @PrePersist
    public void generateId() {
        this.transactionId = String.format("tran-%04d", this.id);
    }
    private String transactionId;
    private String transactionType;
    private String suppliersId;
    private String productCode;
    private LocalDateTime transactionDate;
    private Integer quantity;
    private Long priceUnit;
    private Long totalUnit;
}
