package com.abhaycharanvoice.abhaycharan.Service;

import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;

public interface StockService {
    BaseResponse getStock(String productCode);
}
