package com.abhaycharanvoice.abhaycharan.ServiceImpl;

import com.abhaycharanvoice.abhaycharan.Dto.StockDto;
import com.abhaycharanvoice.abhaycharan.Entity.Stock;
import com.abhaycharanvoice.abhaycharan.Enum.ResponseEnum;
import com.abhaycharanvoice.abhaycharan.Repository.StockRepository;
import com.abhaycharanvoice.abhaycharan.Service.StockService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;
    static Logger log =  LoggerFactory.getLogger(SellServiceImpl.class);

    @Override
    public BaseResponse getStock(String productCode) {
        BaseResponse response = new BaseResponse();
        Optional<Stock> stock;
        try {
            stock = stockRepository.findTopByProductCodeAndActiveFlagOrderByUpdatedTimeDesc(productCode, 1);
            StockDto dto = new StockDto();
            BeanUtils.copyProperties(stock.get(), dto);
            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setStockDto(dto);
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in StockServiceImpl.getStock: "+e.getMessage());
        }
        return response;
    }
}
