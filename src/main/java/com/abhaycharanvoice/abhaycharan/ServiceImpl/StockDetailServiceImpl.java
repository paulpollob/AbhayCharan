package com.abhaycharanvoice.abhaycharan.ServiceImpl;

import com.abhaycharanvoice.abhaycharan.Dto.StockDetailsDto;
import com.abhaycharanvoice.abhaycharan.Entity.StockDetails;
import com.abhaycharanvoice.abhaycharan.Enum.ResponseEnum;
import com.abhaycharanvoice.abhaycharan.Model.Report.StockFilterInfo;
import com.abhaycharanvoice.abhaycharan.Repository.StockDetailsRepository;
import com.abhaycharanvoice.abhaycharan.Service.StockDetailService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StockDetailServiceImpl implements StockDetailService {

    private final StockDetailsRepository stockDetailsRepository;
    static Logger log =  LoggerFactory.getLogger(StockDetailServiceImpl.class);


    public StockDetailServiceImpl(StockDetailsRepository stockDetailsRepository) {
        this.stockDetailsRepository = stockDetailsRepository;
    }

    @Override
    public BaseResponse getStockReport(StockFilterInfo filterInfo) {
        BaseResponse response = new BaseResponse();

        List<StockDetails> stockDetails = new ArrayList<>();


        try {
            if (filterInfo.getSingle()) {
                if (filterInfo.getInvcAndNtDt()) stockDetails = stockDetailsRepository.findByInvoiceNoAndActiveFlag(filterInfo.getInvoiceNo(), 1);
                else stockDetails = stockDetailsRepository.findByCreatedDate(filterInfo.getInvoiceDate());
            } else stockDetails = stockDetailsRepository.findByCreatedDateBetween(filterInfo.getFromDate(), filterInfo.getToDate());


            List<StockDetailsDto> dto = stockDetails.stream().map(this::entityToDto).toList();

            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setStockDetailsDto(dto);
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in ReceiveToStoreServiceImpl.receiveToStore: "+e.getMessage());
        }

        return response;
    }

    private StockDetailsDto entityToDto(StockDetails stockDetails) {
        StockDetailsDto detail = new StockDetailsDto();
        BeanUtils.copyProperties(stockDetails, detail);
        return detail;
    }
}
