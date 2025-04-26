package com.heitormbonfim.www.magic_frige.controller;

import com.heitormbonfim.www.magic_frige.model.FoodItem;
import com.heitormbonfim.www.magic_frige.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createFood(@RequestBody FoodItem newFood) {
        FoodItem newFoodItem = foodService.saveFood(newFood);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFoodItem);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodItem>> getFoods() {
        List<FoodItem> foods = foodService.getAllFoods();
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findFoodById(@PathVariable Long id) {
        Optional<FoodItem> foodFound = foodService.findFoodById(id);
        if (foodFound.isPresent()) {
            FoodItem foodItem = foodFound.get();
            return ResponseEntity.ok(foodItem);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        return foodService.findFoodById(id)
                .map(existing -> {
                    foodItem.setId(existing.getId());
                    FoodItem updated = foodService.saveFood(foodItem);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        foodService.deleteFoodItem(id);
        return ResponseEntity.noContent().build();
    }
}
