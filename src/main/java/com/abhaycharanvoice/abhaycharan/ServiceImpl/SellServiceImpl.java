package com.abhaycharanvoice.abhaycharan.ServiceImpl;

import com.abhaycharanvoice.abhaycharan.Dto.ReceiveToStoreMstDto;
import com.abhaycharanvoice.abhaycharan.Dto.SellProductInfoDto;
import com.abhaycharanvoice.abhaycharan.Dto.SellingInfoDto;
import com.abhaycharanvoice.abhaycharan.Entity.*;
import com.abhaycharanvoice.abhaycharan.Enum.InvoiceType;
import com.abhaycharanvoice.abhaycharan.Enum.ResponseEnum;
import com.abhaycharanvoice.abhaycharan.Model.Report.StockFilterInfo;
import com.abhaycharanvoice.abhaycharan.Model.SellProductInfoModel;
import com.abhaycharanvoice.abhaycharan.Model.SellingInfoModel;
import com.abhaycharanvoice.abhaycharan.Repository.SellRepository;
import com.abhaycharanvoice.abhaycharan.Repository.StockDetailsRepository;
import com.abhaycharanvoice.abhaycharan.Repository.StockRepository;
import com.abhaycharanvoice.abhaycharan.Service.SellService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SellServiceImpl implements SellService {

    static Logger log =  LoggerFactory.getLogger(SellServiceImpl.class);
    private final SellRepository repo;
    private final StockRepository stockRepo;
    private final StockDetailsRepository stockDetailsRepo;
    public SellServiceImpl(SellRepository repo, StockRepository stockRepo, StockDetailsRepository stockDetailsRepo) {
        this.repo = repo;
        this.stockRepo = stockRepo;
        this.stockDetailsRepo = stockDetailsRepo;
    }

    @Override
    public BaseResponse makeSell(SellingInfoModel sellInfoModel) {

        BaseResponse response = new BaseResponse();

        SellingInfo sellingInfo = new SellingInfo();
        BeanUtils.copyProperties(sellInfoModel, sellingInfo);
        sellingInfo.setProductInfo(sellInfoModel.getProductInfo().stream().map(this::modelToEntity).toList());
        sellingInfo.setActiveFlag(1);
        sellingInfo.setCreatedDate(LocalDateTime.now());
        sellingInfo.setCreateOnlyDate(LocalDate.now());


        List<Stock> stocks = new ArrayList<>();
        List<StockDetails> stockDetails = new ArrayList<>();
        for(SellProductInfo info:sellingInfo.getProductInfo())
        {
            Optional<Stock> stock = stockRepo.findTopByProductCodeAndActiveFlagOrderByUpdatedTimeDesc(info.getProductCode(), 1);
            if(stock.isEmpty()) {
                response.setMessage("Out of stock: prod: "+info.getProductCode()+" | "+info.getProductName());
                return response;
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
            info.setSellingInfo(sellingInfo);

            stock.get().setPreviousQty(stock.get().getCurrentQty());
            stock.get().setCurrentQty(stock.get().getCurrentQty()-info.getSellingQty());
            stock.get().setUpdatedTime(LocalDateTime.now());
            stock.get().setActiveFlag(1);
            stock.get().setPreviousQty(stock.get().getCurrentQty());
            stocks.add(stock.get());

            StockDetails stockDetail = new StockDetails();
            stockDetail.setProductCode(info.getProductCode());
            stockDetail.setProductName(info.getProductName());
            stockDetail.setProductCategory(info.getProductCategory());
            stockDetail.setPreviousStockQty(stock.get().getCurrentQty()+info.getSellingQty());
            stockDetail.setCurrentStockQty(stock.get().getCurrentQty());
            stockDetail.setInvoiceType(InvoiceType.DAMAGE_TO_DISTRIBUTOR);
            stockDetail.setInvoiceNo(sellingInfo.getSellId());
            stockDetail.setActiveFlag(1);
            stockDetail.setCreatedDate(LocalDate.now());
            stockDetail.setCreatedTime(LocalDateTime.now());
            stockDetails.add(stockDetail);
        }

        try {

            stockRepo.saveAll(stocks);
            stockDetailsRepo.saveAll(stockDetails);
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

    @Override
    public BaseResponse getSoldProducts(StockFilterInfo filterInfo) {
        BaseResponse response = new BaseResponse();

        List<SellingInfo> sellingInfoList = new ArrayList<>();

        try {
            if (filterInfo.getSingle()) {
                if (filterInfo.getInvcAndNtDt())
                    sellingInfoList = repo.findBySellIdAndActiveFlag(filterInfo.getInvoiceNo(), 1);
                else sellingInfoList = repo.findByCreateOnlyDate(filterInfo.getInvoiceDate());
            } else
                sellingInfoList = repo.findByCreateOnlyDateBetween(filterInfo.getFromDate(), filterInfo.getToDate());

            List<SellingInfoDto> sellingDto = sellingInfoList.stream().map(this::entityToDto).toList();
            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setSellingInfoDtoList(sellingDto);
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in SellServiceImpl.getSoldProducts: "+e.getMessage());
        }
        return response;
    }

    private SellingInfoDto entityToDto(SellingInfo sellingInfo) {
        SellingInfoDto dto = new SellingInfoDto();
        BeanUtils.copyProperties(sellingInfo, dto);
        dto.setProductInfo(sellingInfo.getProductInfo().stream().map(this::entityToDto).toList());
        return dto;
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
