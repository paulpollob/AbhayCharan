package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    private Long id;
    private String customerId;
    private String customerName;
    private String contactNumber;
    private String email;
    private String address;
}
