package com.nourish.backend.controller;

import com.nourish.backend.entity.Meal;
import com.nourish.backend.service.MealService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
@CrossOrigin(origins = "http://localhost:5173")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    // Add a meal
    @PostMapping
    public ResponseEntity<Meal> addMeal(
            @RequestBody Meal meal) {

        return ResponseEntity.ok(
                mealService.addMeal(meal)
        );
    }

    // Get all meals
    @GetMapping
    public ResponseEntity<List<Meal>> getAllMeals() {

        return ResponseEntity.ok(
                mealService.getAllMeals()
        );
    }

    // Get meal by ID
    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                mealService.getMealById(id)
        );
    }

    // Get meals by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Meal>> getMealsByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                mealService.getMealsByUserId(userId)
        );
    }

    // Get meals by user and date
    @GetMapping("/user/{userId}/date/{date}")
    public ResponseEntity<List<Meal>> getMealsByUserIdAndDate(
            @PathVariable Long userId,
            @PathVariable LocalDate date) {

        return ResponseEntity.ok(
                mealService.getMealsByUserIdAndDate(
                        userId,
                        date
                )
        );
    }

    // Update meal
    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(
            @PathVariable Long id,
            @RequestBody Meal meal) {

        return ResponseEntity.ok(
                mealService.updateMeal(id, meal)
        );
    }

    // Delete meal
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeal(
            @PathVariable Long id) {

        mealService.deleteMeal(id);

        return ResponseEntity.ok(
                "Meal deleted successfully"
        );
    }
}