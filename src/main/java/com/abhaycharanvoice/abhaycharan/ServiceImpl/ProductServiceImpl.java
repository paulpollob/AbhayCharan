package com.abhaycharanvoice.abhaycharan.ServiceImpl;

import com.abhaycharanvoice.abhaycharan.Dto.ProductInfoDto;
import com.abhaycharanvoice.abhaycharan.Entity.ProductInfo;
import com.abhaycharanvoice.abhaycharan.Enum.ResponseEnum;
import com.abhaycharanvoice.abhaycharan.Model.ProductInfoModel;
import com.abhaycharanvoice.abhaycharan.Repository.ProductRepository;
import com.abhaycharanvoice.abhaycharan.Service.ProductService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    static Logger log =  LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public BaseResponse addProduct(ProductInfoModel productInfoModel) {
        BaseResponse response = new BaseResponse();

        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productInfoModel, productInfo);
        productInfo.setActiveFlag(1);
        productInfo.setCreatedTime(LocalDateTime.now());

        try {
            productInfo = repo.save(productInfo);

            ProductInfoDto dto = new ProductInfoDto();
            BeanUtils.copyProperties(productInfo, dto);

            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setProductInfoDto(dto);
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in ReceiveToStoreServiceImpl.receiveToStore: "+e.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse findAllProduct() {
        BaseResponse response = new BaseResponse();
        try {
            List<ProductInfo> productInfos = repo.findAllByActiveFlag(1);
            List<ProductInfoDto> dtos = productInfos.stream().map(info-> {
                ProductInfoDto prdctInfDto = new ProductInfoDto();
                BeanUtils.copyProperties(info, prdctInfDto);
                return prdctInfDto;
            }).toList();
            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setProductInfoDtos(dtos);
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in ReceiveToStoreServiceImpl.receiveToStore: "+e.getMessage());
        }

        return response;
    }

    @Override
    public BaseResponse findProduct(String productCode) {
        BaseResponse response = new BaseResponse();
        try {
            Optional<ProductInfo> productInfo = repo.findByProductCodeAndActiveFlag(productCode, 1);
            ProductInfoDto dto = new ProductInfoDto();
            BeanUtils.copyProperties(productInfo.get(), dto);
            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setProductInfoDto(dto);
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in ReceiveToStoreServiceImpl.receiveToStore: "+e.getMessage());
        }

        return response;
    }
}
