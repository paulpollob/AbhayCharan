package com.abhaycharanvoice.abhaycharan.ServiceImpl;

import com.abhaycharanvoice.abhaycharan.Dto.SellProductInfoDto;
import com.abhaycharanvoice.abhaycharan.Dto.SellingInfoDto;
import com.abhaycharanvoice.abhaycharan.Entity.SellProductInfo;
import com.abhaycharanvoice.abhaycharan.Entity.SellingInfo;
import com.abhaycharanvoice.abhaycharan.Entity.Stock;
import com.abhaycharanvoice.abhaycharan.Enum.ResponseEnum;
import com.abhaycharanvoice.abhaycharan.Model.SellProductInfoModel;
import com.abhaycharanvoice.abhaycharan.Model.SellingInfoModel;
import com.abhaycharanvoice.abhaycharan.Repository.SellRepository;
import com.abhaycharanvoice.abhaycharan.Repository.StockRepository;
import com.abhaycharanvoice.abhaycharan.Service.SellService;
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
public class SellServiceImpl implements SellService {

    static Logger log =  LoggerFactory.getLogger(SellServiceImpl.class);
    private final SellRepository repo;
    private final StockRepository stockRepo;
    public SellServiceImpl(SellRepository repo, StockRepository stockRepo) {
        this.repo = repo;
        this.stockRepo = stockRepo;
    }

    @Override
    public BaseResponse makeSell(SellingInfoModel sellInfoModel) {

        BaseResponse response = new BaseResponse();

        SellingInfo sellingInfo = new SellingInfo();
        BeanUtils.copyProperties(sellInfoModel, sellingInfo);
        sellingInfo.setProductInfo(sellInfoModel.getProductInfo().stream().map(this::modelToEntity).toList());
        sellingInfo.setActiveFlag(1);
        sellingInfo.setCreatedDate(LocalDateTime.now());


        List<Stock> stocks = new ArrayList<>();
//        sellingInfo.getProductInfo().forEach(info->
        for(SellProductInfo info:sellingInfo.getProductInfo())
        {
            Optional<Stock> stock = stockRepo.findTopByProductCodeAndActiveFlagOrderByUpdatedTimeDesc(info.getProductCode(), 1);
            if(stock.isEmpty()) {
                stock = Optional.of(new Stock());
                stock.get().setProductCode(info.getProductCode());
            }

            if(stock.get().getCurrentQty()<info.getSellingQty())
            {
                response.setMessage("product (name, code): "+
                        info.getProductCode()+" "+info.getProductName()+
                        " not enough stock requested: "+
                        info.getSellingQty()+" remaining: "+stock.get().getCurrentQty());
                log.info("product (name, code): "+
                        info.getProductCode()+" "+info.getProductName()+
                        " not enough stock requested: "+
                        info.getSellingQty()+" remaining: "+stock.get().getCurrentQty());
                return response;
            }

            stock.get().setPreviousQty(stock.get().getCurrentQty());
            stock.get().setCurrentQty(stock.get().getCurrentQty()-info.getSellingQty());
            stock.get().setUpdatedTime(LocalDateTime.now());
            stock.get().setActiveFlag(1);
            stock.get().setPreviousQty(stock.get().getCurrentQty());

            stocks.add(stock.get());
        }

        try {

            stockRepo.saveAll(stocks);
            sellingInfo = repo.save(sellingInfo);

            SellingInfoDto dto = new SellingInfoDto();
            dto.setProductInfo(sellingInfo.getProductInfo().stream().map(this::entityToDto).toList());

            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setSellingInfoDto(dto);
            return response;
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in SellServiceImpl.makeSell: "+e.getMessage());
        }
        return response;
    }

    private SellProductInfoDto entityToDto(SellProductInfo sellProductInfo) {
        SellProductInfoDto dto = new SellProductInfoDto();
        BeanUtils.copyProperties(sellProductInfo, dto);
        return dto;
    }

    private SellProductInfo modelToEntity(SellProductInfoModel sellProductInfoModel) {
        SellProductInfo prodInfo = new SellProductInfo();
        BeanUtils.copyProperties(sellProductInfoModel, prodInfo);
        return prodInfo;
    }
}
