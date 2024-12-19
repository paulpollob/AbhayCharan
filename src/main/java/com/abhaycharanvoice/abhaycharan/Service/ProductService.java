package com.abhaycharanvoice.abhaycharan.Service;

import com.abhaycharanvoice.abhaycharan.Model.ProductInfoModel;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;

public interface ProductService {

    BaseResponse addProduct(ProductInfoModel productInfo);
    BaseResponse findAllProduct();
    BaseResponse findProduct(String productCode);
    BaseResponse updateProduct(ProductInfoModel productInfo);
}
