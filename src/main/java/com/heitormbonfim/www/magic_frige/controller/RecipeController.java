package com.heitormbonfim.www.magic_frige.controller;

import com.heitormbonfim.www.magic_frige.model.FoodItem;
import com.heitormbonfim.www.magic_frige.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/recipe")
public class RecipeController {
    private final GeminiService geminiService;

    public RecipeController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        return geminiService.generateRecipe();
    }
}
