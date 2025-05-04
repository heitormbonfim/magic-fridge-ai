package com.heitormbonfim.www.magic_frige.controller;

import com.heitormbonfim.www.magic_frige.model.FoodItem;
import com.heitormbonfim.www.magic_frige.service.FoodService;
import com.heitormbonfim.www.magic_frige.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final GeminiService geminiService;
    private final FoodService foodService;

    public RecipeController(GeminiService geminiService, FoodService foodService) {
        this.geminiService = geminiService;
        this.foodService = foodService;
    }

    @GetMapping("/test")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItem> foodItems = foodService.getAllFoods();
        return geminiService.generateRecipe(foodItems)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
