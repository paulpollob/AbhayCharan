package com.abhaycharanvoice.abhaycharan.Service;

import com.abhaycharanvoice.abhaycharan.Model.Report.StockFilterInfo;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;

public interface StockService {
    BaseResponse getStockReport(StockFilterInfo filterInfo);
}
