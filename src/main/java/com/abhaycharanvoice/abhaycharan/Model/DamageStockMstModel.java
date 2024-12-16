package com.abhaycharanvoice.abhaycharan.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DamageStockMstDto {
    private Long id;
    private Integer activeFlag;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
