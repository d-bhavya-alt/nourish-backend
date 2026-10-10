package com.nourish.backend.service;

import com.nourish.backend.entity.DietPlan;
import com.nourish.backend.repository.DietPlanRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietPlanService {

    private final DietPlanRepository dietPlanRepository;

    public DietPlanService(DietPlanRepository dietPlanRepository) {
        this.dietPlanRepository = dietPlanRepository;
    }

    // Add a diet plan
    public DietPlan addDietPlan(DietPlan dietPlan) {
        return dietPlanRepository.save(dietPlan);
    }

    // Get all diet plans
    public List<DietPlan> getAllDietPlans() {
        return dietPlanRepository.findAll();
    }

    // Get diet plan by ID
    public DietPlan getDietPlanById(Long id) {

        return dietPlanRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Diet plan not found"));
    }

    // Update diet plan
    public DietPlan updateDietPlan(
            Long id,
            DietPlan updatedDietPlan) {

        DietPlan existingDietPlan =
                dietPlanRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Diet plan not found"));

        existingDietPlan.setName(updatedDietPlan.getName());
        existingDietPlan.setDescription(
                updatedDietPlan.getDescription());
        existingDietPlan.setGoal(updatedDietPlan.getGoal());
        existingDietPlan.setDietaryPreference(
                updatedDietPlan.getDietaryPreference());
        existingDietPlan.setDailyCalories(
                updatedDietPlan.getDailyCalories());
        existingDietPlan.setDurationDays(
                updatedDietPlan.getDurationDays());

        return dietPlanRepository.save(existingDietPlan);
    }

    // Delete diet plan
    public void deleteDietPlan(Long id) {

        if (!dietPlanRepository.existsById(id)) {
            throw new RuntimeException("Diet plan not found");
        }

        dietPlanRepository.deleteById(id);
    }
}