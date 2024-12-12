package com.abhaycharanvoice.abhaycharan.ServiceImpl;

import com.abhaycharanvoice.abhaycharan.Dto.ReceiveToStoreMstDto;
import com.abhaycharanvoice.abhaycharan.Dto.ReceiveToStoreReceivedProductsDto;
import com.abhaycharanvoice.abhaycharan.Entity.ReceiveToStoreMst;
import com.abhaycharanvoice.abhaycharan.Entity.ReceiveToStoreReceivedProducts;
import com.abhaycharanvoice.abhaycharan.Entity.Stock;
import com.abhaycharanvoice.abhaycharan.Enum.ResponseEnum;
import com.abhaycharanvoice.abhaycharan.Model.ReceiveToStoreProductsDetailsModel;
import com.abhaycharanvoice.abhaycharan.Model.ReceiveToStoreMstModel;
import com.abhaycharanvoice.abhaycharan.Repository.ReceiveToStoreRepo;
import com.abhaycharanvoice.abhaycharan.Repository.StockRepository;
import com.abhaycharanvoice.abhaycharan.Service.ReceiveToStoreService;
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
public class ReceiveToStoreServiceImpl implements ReceiveToStoreService {

    private final ReceiveToStoreRepo repo;
    private final StockRepository stockRepo;
    static final Logger log =  LoggerFactory.getLogger(ReceiveToStoreServiceImpl.class);


    public ReceiveToStoreServiceImpl(ReceiveToStoreRepo repo, StockRepository stockRepo) {
        this.repo = repo;
        this.stockRepo = stockRepo;
    }

    @Override
    @Transactional
    public BaseResponse receiveToStore(ReceiveToStoreMstModel mstMdl) {

        BaseResponse response = new BaseResponse();
        ReceiveToStoreMst entity = modelToEntity(mstMdl);
        entity.setActiveFlag(1);
        entity.setCreateDate(LocalDateTime.now());


        ReceiveToStoreMst finalEntity = entity;
        List<Stock> stocks = new ArrayList<>();
        entity.getProductsDetails().forEach(dtl->{
            Optional<Stock> stock = stockRepo.findTopByProductCodeAndActiveFlagOrderByUpdatedTimeDesc(dtl.getProductCode(), 1);
            if(stock.isEmpty()) {
                stock = Optional.of(new Stock());
                stock.get().setProductCode(dtl.getProductCode());
            }

            stock.get().setPreviousQty(stock.get().getCurrentQty());
            stock.get().setCurrentQty(stock.get().getCurrentQty()+dtl.getProductReceivedQty());
            stock.get().setUpdatedTime(LocalDateTime.now());
            stock.get().setDistributorCode(finalEntity.getDistributorCode());
            stock.get().setTradePrice(dtl.getTradePrice());
            stock.get().setActiveFlag(1);
            stock.get().setMrp(dtl.getMrp());
            stock.get().setPreviousQty(stock.get().getCurrentQty());

            stocks.add(stock.get());
        });



        try {
            stockRepo.saveAll(stocks);
            entity = repo.save(entity);
            ReceiveToStoreMstDto mstDto = new ReceiveToStoreMstDto();
            BeanUtils.copyProperties(entity, mstDto);
            mstDto.setProductsDetails(entity.getProductsDetails().stream().map(this::entityToDto).toList());
            response.setMessage(ResponseEnum.SUCCESS.getStatus());
            response.setRcvDto(mstDto);

        } catch (Exception e) {
            response.setMessage(ResponseEnum.FAILED.getStatus());
            log.info("Error in ReceiveToStoreServiceImpl.receiveToStore: "+e.getMessage());
        }
        return response;
    }

    private ReceiveToStoreMst modelToEntity(ReceiveToStoreMstModel mstMdl) {
        ReceiveToStoreMst entity = new ReceiveToStoreMst();
        BeanUtils.copyProperties(mstMdl, entity);
        entity.setProductsDetails(mstMdl.getProductsDetails().stream().map(d->{
            ReceiveToStoreReceivedProducts rcvPrd = modelToEntity(d);
            rcvPrd.setActiveFlag(1);
            rcvPrd.setCreatedDate(LocalDateTime.now());
            rcvPrd.setReceiveMst(entity);
            return rcvPrd;
        }).toList());
        return entity;
    }

    private ReceiveToStoreReceivedProductsDto entityToDto(ReceiveToStoreReceivedProducts prdDtl) {
        ReceiveToStoreReceivedProductsDto dto = new ReceiveToStoreReceivedProductsDto();
        BeanUtils.copyProperties(prdDtl, dto);
        return dto;
    }

    private ReceiveToStoreReceivedProducts modelToEntity(ReceiveToStoreProductsDetailsModel productsDetails) {
        ReceiveToStoreReceivedProducts model = new ReceiveToStoreReceivedProducts();
        BeanUtils.copyProperties(productsDetails, model);
        return model;
    }
}
