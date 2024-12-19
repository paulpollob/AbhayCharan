package com.abhaycharanvoice.abhaycharan.Controller;

import com.abhaycharanvoice.abhaycharan.Service.StockService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService stockService;
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("getStock/{productCode}")
    public ResponseEntity<BaseResponse> getStock(@PathVariable String productCode){
        return ResponseEntity.ok(stockService.getStock(productCode));
    }
}
