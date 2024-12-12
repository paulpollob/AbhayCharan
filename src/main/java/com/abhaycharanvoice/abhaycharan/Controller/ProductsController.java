package com.abhaycharanvoice.abhaycharan.Controller;

import com.abhaycharanvoice.abhaycharan.Model.ProductInfoModel;
import com.abhaycharanvoice.abhaycharan.Service.ProductService;
import com.abhaycharanvoice.abhaycharan.Util.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/products/")
public class ProductsController {

    private final ProductService productService;
    public ProductsController(ProductService productService){this.productService = productService;}

    @GetMapping("/findAllProduct/")
    public ResponseEntity<BaseResponse> findAllProduct() {
        BaseResponse response = productService.findAllProduct();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findProduct/{productCode}")
    public ResponseEntity<BaseResponse> findProduct(@PathVariable String productCode) {
        BaseResponse response = productService.findProduct(productCode);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addProduct/")
    public ResponseEntity<BaseResponse> addProduct(@RequestBody ProductInfoModel productInfo) {
        BaseResponse response = productService.addProduct(productInfo);
        return ResponseEntity.ok(response);
    }

}
