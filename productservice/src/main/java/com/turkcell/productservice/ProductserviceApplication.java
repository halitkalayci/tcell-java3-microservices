package com.turkcell.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.turkcell.common.events.OrderCreatedEvent;

@SpringBootApplication
public class ProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}


	@KafkaListener(topics = {"orderTopic"})
	public void consumeKafkaMessage(OrderCreatedEvent event)
	{
		System.out.println("Kafka mesajı yakalandı..");
	}
}
