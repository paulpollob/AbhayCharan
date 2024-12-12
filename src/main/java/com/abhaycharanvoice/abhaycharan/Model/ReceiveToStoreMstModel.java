package com.abhaycharanvoice.abhaycharan.Model;

import com.abhaycharanvoice.abhaycharan.Enum.InvoiceType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReceiveToStoreMstModel {
    private String invoiceNo;
    private String storeName;
    private String invoiceDate;
    private String distributorName;
    private String distributorAddress;
    private String distributorPhone;
    private String distributorEmail;
    private String distributorId;
    private InvoiceType invoiceType;
    private List<ReceiveToStoreProductsDetailsModel> productsDetails;
    private Integer activeFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
