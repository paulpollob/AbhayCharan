package com.abhaycharanvoice.abhaycharan.Model;

import com.abhaycharanvoice.abhaycharan.Entity.DamageStockDetail;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DamageStockMstModel {
    private Long id;
    private String invoiceNo;
    private Integer activeFlag;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<DamageStockDetailModel> damageStockDetails;
}
