package com.heitormbonfim.www.magic_frige.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {
    @GetMapping("/hello")
    public ResponseEntity<?> helloWorld() {
        return ResponseEntity.ok("Hello World!");
    }
}
