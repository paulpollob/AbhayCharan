package com.abhaycharanvoice.abhaycharan.ServiceImpl;

import com.abhaycharanvoice.abhaycharan.Model.Report.StockFilterInfo;
import com.abhaycharanvoice.abhaycharan.Repository.StockRepository;
import com.abhaycharanvoice.abhaycharan.Service.StockService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepo;
    public StockServiceImpl(StockRepository stockRepo) {
        this.stockRepo = stockRepo;
    }

    @Override
    public BaseResponse getStockReport(StockFilterInfo filterInfo) {
        BaseResponse response = new BaseResponse();
        return response;
    }
}
