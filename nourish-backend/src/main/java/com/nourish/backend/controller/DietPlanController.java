package com.nourish.backend.controller;

import com.nourish.backend.entity.DietPlan;
import com.nourish.backend.service.DietPlanService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diet-plans")
@CrossOrigin(origins = "http://localhost:5173")
public class DietPlanController {

    private final DietPlanService dietPlanService;

    public DietPlanController(DietPlanService dietPlanService) {
        this.dietPlanService = dietPlanService;
    }

    // Add a diet plan
    @PostMapping
    public ResponseEntity<DietPlan> addDietPlan(
            @RequestBody DietPlan dietPlan) {

        return ResponseEntity.ok(
                dietPlanService.addDietPlan(dietPlan)
        );
    }

    // Get all diet plans
    @GetMapping
    public ResponseEntity<List<DietPlan>> getAllDietPlans() {

        return ResponseEntity.ok(
                dietPlanService.getAllDietPlans()
        );
    }

    // Get diet plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<DietPlan> getDietPlanById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                dietPlanService.getDietPlanById(id)
        );
    }

    // Update diet plan
    @PutMapping("/{id}")
    public ResponseEntity<DietPlan> updateDietPlan(
            @PathVariable Long id,
            @RequestBody DietPlan dietPlan) {

        return ResponseEntity.ok(
                dietPlanService.updateDietPlan(id, dietPlan)
        );
    }

    // Delete diet plan
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDietPlan(
            @PathVariable Long id) {

        dietPlanService.deleteDietPlan(id);

        return ResponseEntity.ok(
                "Diet plan deleted successfully"
        );
    }
}