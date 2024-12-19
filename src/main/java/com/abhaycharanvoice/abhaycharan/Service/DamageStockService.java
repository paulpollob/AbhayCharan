package com.abhaycharanvoice.abhaycharan.Service;

import com.abhaycharanvoice.abhaycharan.Entity.DamageStockMst;
import com.abhaycharanvoice.abhaycharan.Model.DamageStockDetailModel;
import com.abhaycharanvoice.abhaycharan.Model.DamageStockMstModel;
import com.abhaycharanvoice.abhaycharan.Model.Report.StockFilterInfo;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;

import java.util.List;

public interface DamageStockService {
    BaseResponse returnDamageProduct(DamageStockMstModel dmgStockMdl);

    BaseResponse getDamageProducts(StockFilterInfo filterInfo);

    BaseResponse getDamageProductsDetail(StockFilterInfo filterInfo);
}
