package com.abhaycharanvoice.abhaycharan.Controller;

import com.abhaycharanvoice.abhaycharan.Entity.DamageStockMst;
import com.abhaycharanvoice.abhaycharan.Model.DamageStockDetailModel;
import com.abhaycharanvoice.abhaycharan.Model.DamageStockMstModel;
import com.abhaycharanvoice.abhaycharan.Model.Report.StockFilterInfo;
import com.abhaycharanvoice.abhaycharan.Service.DamageStockService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/damage")
public class DamageStockController {

    private final DamageStockService damageStockService;

    public DamageStockController(DamageStockService damageStockService) {
        this.damageStockService = damageStockService;
    }

    @PostMapping("/returnDamageProduct")
    public ResponseEntity<BaseResponse> returnDamageProduct(@RequestBody DamageStockMstModel dmgStockMdl) {
        return ResponseEntity.ok(damageStockService.returnDamageProduct(dmgStockMdl));
    }

    @PostMapping("/getDamageProducts")
    public ResponseEntity<BaseResponse> getDamageProducts(@RequestBody StockFilterInfo filterInfo){
        BaseResponse response = damageStockService.getDamageProducts(filterInfo);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/getDamageProductsDetail")
    public ResponseEntity<BaseResponse> getDamageProductsDetail(@RequestBody StockFilterInfo filterInfo){
        BaseResponse response = damageStockService.getDamageProductsDetail(filterInfo);
        return ResponseEntity.ok(response);
    }

}
