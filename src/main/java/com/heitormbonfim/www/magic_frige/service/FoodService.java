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

    public FoodItem updateFoodItem(Long id, FoodItem foodItem) {
        Optional<FoodItem> foodItemFound = foodItemRepository.findById(id);
        if (foodItemFound.isPresent()) {
            FoodItem foodItemToUpdate = foodItemFound.get();
            foodItem.setId(foodItemToUpdate.getId());
            foodItemRepository.save(foodItem);
            return foodItem;
        }
        return null;
    }

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
}
