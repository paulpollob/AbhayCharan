package com.abhaycharanvoice.abhaycharan.Model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SellingInfoModel {
    private String sellId;
    private String authorId;
    private String customerName;
    private String customerPhoneNumber;
    private String customerAddress;
    private String customerEmail;
    private Integer activeFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    List<SellProductInfoModel> productInfo;
}
