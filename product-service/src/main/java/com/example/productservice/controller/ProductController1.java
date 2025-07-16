
package com.example.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products1")
public class ProductController1 {
    @GetMapping
    public ResponseEntity<List<String>> getProducts() {
        return ResponseEntity.ok(List.of("Product A", "Product B", "Product C"));
    }
}
