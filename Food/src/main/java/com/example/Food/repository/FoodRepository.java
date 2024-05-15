package com.example.Food.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Food.model.Food;
// import com.example.Food.model.NutritionItem;

public interface FoodRepository extends JpaRepository<Food, Integer> {}
// public interface NutritionItem extends JpaRepository<NutritionItem, Interger> {}