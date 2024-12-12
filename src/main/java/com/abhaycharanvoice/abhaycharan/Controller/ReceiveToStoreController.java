package com.abhaycharanvoice.abhaycharan.Controller;

import com.abhaycharanvoice.abhaycharan.Model.ReceiveToStoreMstModel;
import com.abhaycharanvoice.abhaycharan.Service.ReceiveToStoreService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/receiveToStore")
public class ReceiveToStoreController {

    private final ReceiveToStoreService receiveToStoreService;

    public ReceiveToStoreController(ReceiveToStoreService receiveToStoreService) {
        this.receiveToStoreService = receiveToStoreService;
    }

    @PostMapping("/receiveProducts")
    public ResponseEntity<BaseResponse> storeNewInvoice(@RequestBody ReceiveToStoreMstModel mstMdl){
        BaseResponse response = receiveToStoreService.receiveToStore(mstMdl);
        return ResponseEntity.ok(response);
    }
}
