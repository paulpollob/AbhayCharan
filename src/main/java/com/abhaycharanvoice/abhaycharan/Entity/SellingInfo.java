package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Data
@Entity
public class SellingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "invsl_seq")
    @SequenceGenerator(name = "invsl_seq", sequenceName = "invsl_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    @PrePersist
    public void generateId() {
        this.sellId = String.format("invsl-%04d", this.id);
    }
    private String sellId;
    private String authorId;
    private String customerName;
    private String customerPhoneNumber;
    private String customerAddress;
    private String customerEmail;
    private Integer activeFlag;
    private LocalDateTime createdDate;
    private LocalDate createOnlyDate;
    private LocalDateTime updatedDate;
    @OneToMany(mappedBy = "sellingInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<SellProductInfo> productInfo;
}
