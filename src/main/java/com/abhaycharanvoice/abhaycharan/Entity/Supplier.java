package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sup_seq")
    @SequenceGenerator(name = "sup_seq", sequenceName = "sup_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    @PrePersist
    public void generateId() {
        this.supplierId = String.format("prod-%04d", this.id);
    }
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String email;
    private String address;
}
