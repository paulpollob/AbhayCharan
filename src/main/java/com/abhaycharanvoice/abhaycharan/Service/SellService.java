package com.abhaycharanvoice.abhaycharan.Service;

import com.abhaycharanvoice.abhaycharan.Model.SellingInfoModel;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;

public interface SellService {
    BaseResponse makeSell(SellingInfoModel sellInfoModel);
}