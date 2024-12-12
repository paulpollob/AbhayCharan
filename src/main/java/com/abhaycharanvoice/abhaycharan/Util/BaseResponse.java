package com.abhaycharanvoice.abhaycharan.Util;

import com.abhaycharanvoice.abhaycharan.Dto.ProductInfoDto;
import com.abhaycharanvoice.abhaycharan.Dto.ReceiveToStoreMstDto;
import com.abhaycharanvoice.abhaycharan.Dto.SellingInfoDto;
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
}
