package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    private Long id;
    private String transactionId;
    private String transactionType;
    private String suppliersId;
    private String productCode;
    private LocalDateTime transactionDate;
    private Integer quantity;
    private Long priceUnit;
    private Long totalUnit;
}
