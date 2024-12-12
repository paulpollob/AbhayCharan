package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    private String customerId;
    @Transient // This field will not be persisted
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long sequenceValue;
    @PrePersist
    public void generateId() {
        this.customerId = String.format("cstmr-%04d", this.sequenceValue);
    }
    private String customerName;
    private String contactNumber;
    private String email;
    private String address;
}
