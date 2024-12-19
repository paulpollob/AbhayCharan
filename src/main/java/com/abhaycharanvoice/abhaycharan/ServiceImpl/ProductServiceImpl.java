package com.abhaycharanvoice.abhaycharan.ServiceImpl;

import com.abhaycharanvoice.abhaycharan.Dto.ProductInfoDto;
import com.abhaycharanvoice.abhaycharan.Entity.ProductInfo;
import com.abhaycharanvoice.abhaycharan.Enum.ResponseEnum;
import com.abhaycharanvoice.abhaycharan.Model.ProductInfoModel;
import com.abhaycharanvoice.abhaycharan.Repository.ProductRepository;
import com.abhaycharanvoice.abhaycharan.Service.ProductService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import jakarta.transaction.Transactional;
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

    @Transactional
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
            log.info("Error in ProductServiceImpl.addProduct: "+e.getMessage());
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
            log.info("Error in ProductServiceImpl.findAllProduct: "+e.getMessage());
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
            log.info("Error in ProductServiceImpl.findProduct: "+e.getMessage());
        }

        return response;
    }

    @Override
    public BaseResponse updateProduct(ProductInfoModel productInfoModel) {
        BaseResponse response = new BaseResponse();

        if(productInfoModel.getProductCode()==null)
        {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            return response;
        }

        Optional<ProductInfo> productInfo = repo.findByProductCodeAndActiveFlag(productInfoModel.getProductCode(), 1);

        productInfo = Optional.of(prepare(productInfo.get(), productInfoModel));
        BeanUtils.copyProperties(productInfoModel, productInfo);
        productInfo.get().setUpdatedTime(LocalDateTime.now());

        try {
            productInfo = Optional.of(repo.save(productInfo.get()));

            ProductInfoDto dto = new ProductInfoDto();
            BeanUtils.copyProperties(productInfo.get(), dto);

            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setProductInfoDto(dto);
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in ProductServiceImpl.updateProduct: "+e.getMessage());
        }
        return response;
    }

    private ProductInfo prepare(ProductInfo productInfo, ProductInfoModel productInfoModel){
        if(productInfoModel.getProductName()!=null) productInfo.setProductName(productInfoModel.getProductName());
        if(productInfoModel.getProductCode()!=null) productInfo.setProductCode(productInfoModel.getProductCode());
        if(productInfoModel.getMrp()!=null) productInfo.setMrp(productInfoModel.getMrp());
        if(productInfoModel.getTradePrice()!=null) productInfo.setTradePrice(productInfoModel.getTradePrice());
        if(productInfoModel.getPackSize()!=null) productInfo.setPackSize(productInfoModel.getPackSize());
        return productInfo;
    }
}
