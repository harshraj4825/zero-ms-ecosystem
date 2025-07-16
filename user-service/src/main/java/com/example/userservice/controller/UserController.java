package com.example.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ProductClient productClient;

//    @GetMapping("/{id}")
//    public Mono<Map<String, Object>> getUserWithProducts(@PathVariable("id") String id) {
//        return productClient.getProducts()
//                .map(products -> {
//                    Map<String, Object> response = new HashMap<>();
//                    response.put("id", id);
//                    response.put("name", "Reactive User");
//                    response.put("products", products);
//                    return response;
//                });
//    }

    @GetMapping("/{id}")
    public Mono<Map<String, Object>> getUserWithProducts(@PathVariable("id") String id) {
        return productClient.getProducts()
                .map(products -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", id);
                    response.put("name", "Reactive User");
                    response.put("products", products);
                    return response;
                });
    }

}

