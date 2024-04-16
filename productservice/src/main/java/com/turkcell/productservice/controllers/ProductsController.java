package com.turkcell.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @GetMapping
    public int getStock(@RequestParam int productId)
    {
        System.out.println("Bir istek geldi:" + productId);

        try{
            Thread.sleep(10000);
        }
        catch(Exception e) {}


        if(productId > 3)
            return 0;
        return 5;
    }
}
