package com.turkcell.orderservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="productservice", url="http://localhost:8082")
public interface ProductServiceClient
{
    @GetMapping("/api/v1/products")
    int getStockByProductId(@RequestParam int productId);

}
