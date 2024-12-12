package com.abhaycharanvoice.abhaycharan.Service;

import com.abhaycharanvoice.abhaycharan.Model.ReceiveToStoreMstModel;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;

public interface ReceiveToStoreService {
    BaseResponse receiveToStore(ReceiveToStoreMstModel mstMdl);
}
