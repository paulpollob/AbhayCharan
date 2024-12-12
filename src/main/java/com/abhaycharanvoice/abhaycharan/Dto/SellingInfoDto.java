package com.abhaycharanvoice.abhaycharan.Dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SellingInfoDto {
    private String sellId;
    private String authorId;
    private String customerName;
    private String customerPhoneNumber;
    private String customerAddress;
    private String customerEmail;
    private Integer activeFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    List<SellProductInfoDto> productInfo;
}
