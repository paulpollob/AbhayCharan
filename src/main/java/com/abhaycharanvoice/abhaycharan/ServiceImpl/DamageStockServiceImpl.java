package com.abhaycharanvoice.abhaycharan.ServiceImpl;

import com.abhaycharanvoice.abhaycharan.Dto.DamageStockDetailDto;
import com.abhaycharanvoice.abhaycharan.Dto.DamageStockMstDto;
import com.abhaycharanvoice.abhaycharan.Dto.SellingInfoDto;
import com.abhaycharanvoice.abhaycharan.Entity.*;
import com.abhaycharanvoice.abhaycharan.Enum.InvoiceType;
import com.abhaycharanvoice.abhaycharan.Enum.ResponseEnum;
import com.abhaycharanvoice.abhaycharan.Model.DamageStockDetailModel;
import com.abhaycharanvoice.abhaycharan.Model.DamageStockMstModel;
import com.abhaycharanvoice.abhaycharan.Model.Report.StockFilterInfo;
import com.abhaycharanvoice.abhaycharan.Repository.DamageStockRepo;
import com.abhaycharanvoice.abhaycharan.Repository.DamagedStockDetailRepo;
import com.abhaycharanvoice.abhaycharan.Repository.StockDetailsRepository;
import com.abhaycharanvoice.abhaycharan.Repository.StockRepository;
import com.abhaycharanvoice.abhaycharan.Service.DamageStockService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import com.abhaycharanvoice.abhaycharan.Util.GenerateValue;
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
public class DamageStockServiceImpl implements DamageStockService {

    @Autowired
    private DamageStockRepo damageStockRepo;
    @Autowired
    private StockRepository stockRepo;
    @Autowired
    private DamagedStockDetailRepo detailRepo;
    @Autowired
    private StockDetailsRepository stockDetailsRepo;

    static Logger log =  LoggerFactory.getLogger(DamageStockServiceImpl.class);

    @Override
    public BaseResponse returnDamageProduct(DamageStockMstModel dmgStockMdl) {
        BaseResponse response = new BaseResponse();

        dmgStockMdl.setInvoiceNo(GenerateValue.generateDamageInvoice());
        DamageStockMst dmgMst = modelToEntity(dmgStockMdl);
        dmgMst.setActiveFlag(1);
        dmgMst.setCreatedAt(LocalDateTime.now());
        dmgMst.setCreatedDate(LocalDate.now());

        List<Stock> stocks = new ArrayList<>();
        List<StockDetails> stockDetails = new ArrayList<>();
        for(DamageStockDetail mst:dmgMst.getDamageStockDetails() )
        {
            Optional<Stock> stock = stockRepo.findTopByProductCodeAndActiveFlagOrderByUpdatedTimeDesc(mst.getProductCode(), 1);
            if(stock.isEmpty()) {
                response.setMessage("Out of stock: prod: "+mst.getProductCode()+" | "+mst.getProductName());
                return response;
            }

            if(stock.get().getCurrentQty()<mst.getDamageQty())
            {
                response.setMessage("Qty: "+stock.get().getCurrentQty()+"  "+
                        mst.getProductCode()+" "+mst.getProductName()+
                        " remaining");
                log.info("product (name, code): "+
                        mst.getProductCode()+" "+mst.getProductName()+
                        " not enough stock requested: "+
                        mst.getDamageQty()+" remaining: "+stock.get().getCurrentQty());
                return response;
            }

            stock.get().setPreviousQty(stock.get().getCurrentQty());
            stock.get().setCurrentQty(stock.get().getCurrentQty()-mst.getDamageQty());
            stock.get().setUpdatedTime(LocalDateTime.now());
            stock.get().setActiveFlag(1);
            stock.get().setPreviousQty(stock.get().getCurrentQty());
            stocks.add(stock.get());

            StockDetails stockDetail = new StockDetails();
            stockDetail.setProductCode(mst.getProductCode());
            stockDetail.setProductName(mst.getProductName());
            stockDetail.setPreviousStockQty(stock.get().getCurrentQty()+mst.getDamageQty());
            stockDetail.setCurrentStockQty(stock.get().getCurrentQty());
            stockDetail.setInvoiceType(InvoiceType.DAMAGE_TO_DISTRIBUTOR);
            stockDetail.setInvoiceNo(dmgMst.getInvoiceNo());
            stockDetail.setActiveFlag(1);
            stockDetail.setCreatedDate(LocalDate.now());
            stockDetail.setCreatedTime(LocalDateTime.now());
            stockDetails.add(stockDetail);
        }


        try {
            System.out.println(("HK damaged data: "+dmgMst));
            stockRepo.saveAll(stocks);
            dmgMst = damageStockRepo.save(dmgMst);
            stockDetailsRepo.saveAll(stockDetails);
            DamageStockMstDto dto = entityToDto(dmgMst);

            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setDamageMstDto(dto);

            return response;
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in DamageStockServiceImpl.returnDamageProduct: "+e.getMessage());
            return response;
        }
    }

    @Override
    public BaseResponse getDamageProducts(StockFilterInfo filterInfo) {
        BaseResponse response = new BaseResponse();

        List<DamageStockMst> damageInfoList = new ArrayList<>();

        try {
            if (filterInfo.getSingle()) {
                if (filterInfo.getInvcAndNtDt())
                    damageInfoList = damageStockRepo.findByInvoiceNoAndActiveFlag(filterInfo.getInvoiceNo(), 1);
                else damageInfoList = damageStockRepo.findByCreatedDate(filterInfo.getInvoiceDate());
            } else
                damageInfoList = damageStockRepo.findByCreatedDateBetween(filterInfo.getFromDate(), filterInfo.getToDate());

            List<DamageStockMstDto> dto = damageInfoList.stream().map(this::entityToDto).toList();
            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setDamageStockMstDtoList(dto);
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in SellServiceImpl.getSoldProducts: "+e.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse getDamageProductsDetail(StockFilterInfo filterInfo) {
        BaseResponse response = new BaseResponse();

        List<DamageStockDetail> damageInfoList = new ArrayList<>();

        try {
            if (filterInfo.getSingle()) {
                if (filterInfo.getInvcAndNtDt())
                    damageInfoList = detailRepo.findByInvoiceNoAndActiveFlag(filterInfo.getInvoiceNo(), 1);
                else damageInfoList = detailRepo.findByCreatedDate(filterInfo.getInvoiceDate());
            } else
                damageInfoList = detailRepo.findByCreatedDateBetween(filterInfo.getFromDate(), filterInfo.getToDate());

            List<DamageStockDetailDto> dto = damageInfoList.stream().map(this::entityToDto).toList();
            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setDetailDtoList(dto);
        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in SellServiceImpl.getSoldProducts: "+e.getMessage());
        }
        return response;
    }

    private DamageStockMstDto entityToDto(DamageStockMst dmgMst) {
        DamageStockMstDto dto = new DamageStockMstDto();
        BeanUtils.copyProperties(dmgMst, dto);
        dto.setDamageStockDetails(dmgMst.getDamageStockDetails().stream().map(this::entityToDto).toList());
        return dto;
    }

    private DamageStockMst modelToEntity(DamageStockMstModel dmgStockMdl) {
        DamageStockMst mst = new DamageStockMst();
        BeanUtils.copyProperties(dmgStockMdl, mst);
        mst.setDamageStockDetails(dmgStockMdl.getDamageStockDetails().stream().map(d->{
            DamageStockDetail dtls = modelToEntity(d);
            dtls.setInvoiceNo(mst.getInvoiceNo());
            dtls.setCreatedAt(LocalDateTime.now());
            dtls.setCreatedDate(LocalDate.now());
            dtls.setActiveFlag(1);
            dtls.setDamageStockMst(mst);
            return dtls;
        }).toList());
//        mst.setDamageStockDetails(dmgStockMdl.getDamageStockDetails().stream().map((dtl)=>{
//                DamageStockDetail dtls = modelToEntity(dtl)
//        }).toList());
        return mst;
    }

    private DamageStockDetailDto entityToDto(DamageStockDetail damageStockMst) {
        DamageStockDetailDto dto = new DamageStockDetailDto();
        BeanUtils.copyProperties(damageStockMst, dto);
        return dto;
    }

    private DamageStockDetail modelToEntity(DamageStockDetailModel damageStockMstModel) {
        DamageStockDetail damageStockMst = new DamageStockDetail();
        BeanUtils.copyProperties(damageStockMstModel, damageStockMst);
        damageStockMst.setCreatedAt(LocalDateTime.now());
        damageStockMst.setActiveFlag(1);
        return damageStockMst;
    }
}
