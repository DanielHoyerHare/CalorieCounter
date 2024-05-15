package com.example.Food.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Food.model.Food;
import com.example.Food.repository.FoodRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final FoodRepository foodRepository;

    @Autowired
    public DatabaseSeeder(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Add sample data here
        Food food1 = new Food();
        
        food1.setName("Æble");
        food1.carbohydrate = (float) 14;
        food1.fat = (float) 0.17;
        food1.protein = (float) 0.26;
        food1.energy = (float) 31;
        food1.salt = (float) 0;

        foodRepository.save(food1);
    }
}