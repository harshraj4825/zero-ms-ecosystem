package com.example.userservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductClient {

    @Autowired
    private WebClient.Builder webClientBuilder;


    @CircuitBreaker(name = "productServiceCB", fallbackMethod = "fallbackProductsCB")
    public Mono<List<Product>> getProducts() {
       return getProductWithRetry();
    }

    @Retry(name = "productServiceRetry", fallbackMethod = "fallbackProducts")
    private Mono<List<Product>> getProductWithRetry(){
        return webClientBuilder.build()
                .get()
                .uri("http://PRODUCT-SERVICE/products")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList();
    }

    // Fallback for Circuit Breaker
    public Mono<List<Product>> fallbackProductsCB(Throwable t) {
        System.out.println("CircuitBreaker fallback due to CB: " + t.getMessage());
        return Mono.just(List.of());
    }

    public Mono<List<Product>> fallbackProducts(Throwable t) {
        System.out.println("fallback due retry" + t.getMessage());
        return Mono.just(List.of());
    }
}
