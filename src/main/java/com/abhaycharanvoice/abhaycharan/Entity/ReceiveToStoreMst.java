package com.abhaycharanvoice.abhaycharan.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveToStoreMst {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "receive_to_store_mst_seq")
    @SequenceGenerator(name = "receive_to_store_mst_seq", sequenceName = "receive_to_store_mst_seq",initialValue = 10000,allocationSize = 1 )
    private Long id;
    @PrePersist
    public void generateId() {
        this.invoiceNo = String.format("rcvinv-%04d", this.id);
    }
    private String invoiceNo;
    private String storeName;
    private String invoiceDate;
    private String distributorName;
    private String distributorAddress;
    private String distributorPhone;
    private String distributorEmail;
    private String distributorCode;
    private LocalDateTime createDate;
    private LocalDate createdDate;
    private LocalDateTime updateDate;
    private Integer activeFlag;

    @OneToMany(mappedBy = "receiveMst", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ReceiveToStoreReceivedProducts> productsDetails;
}
