package com.nourish.backend.controller;

import com.nourish.backend.entity.FoodItem;
import com.nourish.backend.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-items")
@CrossOrigin(origins = "http://localhost:5175")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    // GET ALL FOOD ITEMS
    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    // GET FOOD ITEM BY ID
    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    foodItemService.getFoodItemById(id)
            );
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ADD FOOD ITEM
    @PostMapping
    public FoodItem addFoodItem(
            @RequestBody FoodItem foodItem) {

        return foodItemService.addFoodItem(foodItem);
    }

    // DELETE FOOD ITEM
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodItem(
            @PathVariable Long id) {

        try {
            foodItemService.deleteFoodItem(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}