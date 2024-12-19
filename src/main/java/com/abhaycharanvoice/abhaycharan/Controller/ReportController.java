package com.abhaycharanvoice.abhaycharan.Controller;

import com.abhaycharanvoice.abhaycharan.Model.Report.StockFilterInfo;
import com.abhaycharanvoice.abhaycharan.Service.StockDetailService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/report/")
public class ReportController {

    private final StockDetailService stockService;
    public ReportController(StockDetailService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/getStockDetails")
    public ResponseEntity<BaseResponse> getStockDetails(@RequestBody StockFilterInfo filterInfo){

        BaseResponse response = stockService.getStockReport(filterInfo);
        return ResponseEntity.ok(response);

    }
}
