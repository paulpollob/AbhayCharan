package com.abhaycharanvoice.abhaycharan.Dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DamageStockMstDto {
    private Long id;
    private Integer activeFlag;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<DamageStockDetailDto> damageStockDetails;
}
