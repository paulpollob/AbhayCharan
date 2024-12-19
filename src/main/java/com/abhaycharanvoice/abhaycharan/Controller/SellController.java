package com.abhaycharanvoice.abhaycharan.Controller;

import com.abhaycharanvoice.abhaycharan.Model.Report.StockFilterInfo;
import com.abhaycharanvoice.abhaycharan.Model.SellingInfoModel;
import com.abhaycharanvoice.abhaycharan.Service.SellService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sell/")
public class SellController {

    private final SellService service;

    public SellController(SellService service) {
        this.service = service;
    }

    @PostMapping("/makeSell/")
    public ResponseEntity<BaseResponse> makeSell(@RequestBody SellingInfoModel sellInfoModel){
        BaseResponse response = service.makeSell(sellInfoModel);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getSoldProducts")
    public ResponseEntity<BaseResponse> getSoldProducts(@RequestBody StockFilterInfo filterInfo){
        BaseResponse response = service.getSoldProducts(filterInfo);
        return ResponseEntity.ok(response);
    }

}
