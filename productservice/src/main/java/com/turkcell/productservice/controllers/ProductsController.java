package com.turkcell.productservice.controllers;

import com.turkcell.core.ExampleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    // /api/v1/products?productId=5

    @GetMapping
    public int getStock(@RequestParam int productId)
    {
        System.out.println("Bir istek geldi:" + productId);

       try{
      //      Thread.sleep(10000);
        }
        catch(Exception e) {}

        if(productId > 3)
            return 0;
        return 5;
    }
    // /api/v1/products/1
    @GetMapping("{id}")
    public int test(@PathVariable int id)
    {
        return id;
    }

    @GetMapping("test")
    public String get()
    {
        return "";
    }
}
