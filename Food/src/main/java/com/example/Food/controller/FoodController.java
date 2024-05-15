package com.example.Food.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Food.model.Food;
import com.example.Food.repository.FoodRepository;

@RestController
@RequestMapping("/Food")
public class FoodController {
    
    FoodRepository repo;

    FoodController(FoodRepository foodRepository){
        this.repo = foodRepository;
    }


    @GetMapping()
    List<Food> getAllFoods(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    // Food read(@PathVariable int id) {
    //     return repo.findById(id).get();
    // }
    ResponseEntity<Food> read(@PathVariable int id) {
        Optional<Food> x = repo.findById(id);
        if (x.isPresent()) {
            return ResponseEntity.ok(x.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    void create(@RequestBody Food creature) {
        repo.save(creature);
    }

    @PutMapping()
    void update(@RequestBody Food creature) {
        repo.save(creature);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        repo.deleteById(id);
    }
    
    


}
