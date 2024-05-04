package com.turkcell.orderservice.controllers;

import com.turkcell.orderservice.clients.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.turkcell.common.events.OrderCreatedEvent;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    //private final WebClient.Builder webClientBuilder;
    private final ProductServiceClient productServiceClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    public String addOrder(@RequestParam int productId)
    {
        // ... Ürün servisine gidip ürün(ler)ün stok bilgisi çekilmeli.
        /*
        var result = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8082/api/v1/products?productId="+productId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        System.out.println("Ürün servisinden gelen cevap: " + result);

        if(result <= 0)
            throw new RuntimeException("Ürün stokta yok");
        */

        int stockResult = productServiceClient.getStockByProductId(productId);

        System.out.println("Ürün servisinden gelen cevap: " + stockResult);

        if(stockResult <= 0)
            throw new RuntimeException("Ürün stokta yok");


        // Kafka mesajı gönder.
        // Default topic dışında bir topic kullanılması istendiğinde kullanılabilir.
        // kafkaTemplate.send("orderTopic", "NewOrder", new OrderCreatedEvent(1, LocalDateTime.now().minusDays(3)));

        kafkaTemplate.sendDefault("NewOrder", new OrderCreatedEvent(1, LocalDateTime.now().minusDays(3)));
        return "Sipariş eklendi";
    }
}
