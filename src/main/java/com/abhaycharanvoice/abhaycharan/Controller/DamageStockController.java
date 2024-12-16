package com.abhaycharanvoice.abhaycharan.Controller;

import com.abhaycharanvoice.abhaycharan.Model.DamageStockMstModel;
import com.abhaycharanvoice.abhaycharan.Service.DamageStockService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/damage")
public class DamageStock {

    private DamageStockService damageStockService;

    public DamageStock(DamageStockService damageStockService) {
        this.damageStockService = damageStockService;
    }

    @PostMapping("/returnDamageProduct")
    public ResponseEntity<BaseResponse> returnDamageProduct(@RequestBody DamageStockMstModel damageStockMstModel) {
        BaseResponse baseResponse = new BaseResponse();
        return damageStockService.returnDamageProduct(damageStockMstModel);
    }

}
