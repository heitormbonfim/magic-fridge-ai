package com.heitormbonfim.www.magic_frige.service;

import com.heitormbonfim.www.magic_frige.model.FoodItem;
import com.heitormbonfim.www.magic_frige.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodItemRepository foodItemRepository;

    public FoodService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem saveFood(FoodItem foodItem){
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> getAllFoods(){
        return foodItemRepository.findAll();
    }

    public Optional<FoodItem> findFoodById(Long id) {
        return foodItemRepository.findById(id);
    }

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
}
