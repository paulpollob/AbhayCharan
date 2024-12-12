package com.abhaycharanvoice.abhaycharan.Dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveToStoreMstDto {
    private String invoiceNo;
    private String storeName;
    private String invoiceDate;
    private String distributorName;
    private String distributorAddress;
    private String distributorPhone;
    private String distributorEmail;
    private String distributorId;
    private Long productId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Integer activeFlag;
    private List<ReceiveToStoreReceivedProductsDto> productsDetails;
}
