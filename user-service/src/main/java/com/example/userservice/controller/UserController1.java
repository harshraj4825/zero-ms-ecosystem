
package com.example.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users1")
public class UserController1 {
    @GetMapping
    public ResponseEntity<List<String>> getUsers() {
        return ResponseEntity.ok(List.of("Zero", "Alpha", "Beta"));
    }
}
