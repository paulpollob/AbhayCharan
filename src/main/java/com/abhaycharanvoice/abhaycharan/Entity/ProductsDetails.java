package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProductsDetails {
    @Id
    private Long id;
    private String productCode;
    private String productName;
    private Long productQty;
    private Long productPrice;
    private String category;
    private String description;
}
