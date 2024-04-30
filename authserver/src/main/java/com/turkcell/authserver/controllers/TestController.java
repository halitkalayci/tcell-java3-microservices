package com.turkcell.authserver.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping
    public String get(){
        return "Merhaba";
    }
    @PostMapping
    public String post()
    {
        return "Test.Post";
    }
    @PutMapping
    public String put()
    {
        return "Test.Put";
    }

    @DeleteMapping
    public String delete()
    {
        return "Test.Delete";
    }
}
