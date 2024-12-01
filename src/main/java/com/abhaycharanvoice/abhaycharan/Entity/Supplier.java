package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Supplier {
    @Id
    private Long id;
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String email;
    private String address;
}
