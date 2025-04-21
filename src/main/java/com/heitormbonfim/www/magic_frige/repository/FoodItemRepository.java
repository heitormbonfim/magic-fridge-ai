package com.heitormbonfim.www.magic_frige.repository;

import com.heitormbonfim.www.magic_frige.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
