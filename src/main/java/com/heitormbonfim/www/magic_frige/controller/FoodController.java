package com.heitormbonfim.www.magic_frige.controller;

import com.heitormbonfim.www.magic_frige.model.FoodItem;
import com.heitormbonfim.www.magic_frige.service.FoodService;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/add")
    public RequestEntity<?> createFood(@RequestBody FoodItem newFood) {
        return null;
    }

    public RequestEntity<?> getFoods() {
        return null;
    }

    public RequestEntity<?> findFoodById() {
        return null;
    }

    public RequestEntity<?> updateFood() {
        return null;
    }

    public RequestEntity<?> deleteFood() {
        return null;
    }
}
