package com.abhaycharanvoice.abhaycharan.Util;

import com.abhaycharanvoice.abhaycharan.Dto.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private String message;
    private ReceiveToStoreMstDto rcvDto;
    private ProductInfoDto productInfoDto;
    private List<ProductInfoDto> productInfoDtos;
    private SellingInfoDto sellingInfoDto;
    private DamageStockMstDto damageMstDto;
    private List<StockDetailsDto> stockDetailsDto;
    private List<ReceiveToStoreMstDto> receiveMstDto;
    private List<SellingInfoDto> sellingInfoDtoList;
    private List<DamageStockMstDto> damageStockMstDtoList;
    private List<DamageStockDetailDto> detailDtoList;
    private StockDto stockDto;
}
