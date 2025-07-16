package com.example.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> getAllProducts() {
        return List.of(
            new Product("1", "Keyboard", 450.0),
            new Product("2", "Mouse", 250.0),
            new Product("3", "Monitor", 7500.0)
        );
    }
}
