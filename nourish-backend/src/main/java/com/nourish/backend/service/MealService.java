package com.nourish.backend.service;

import com.nourish.backend.entity.Meal;
import com.nourish.backend.entity.FoodItem;
import com.nourish.backend.repository.MealRepository;
import com.nourish.backend.repository.FoodItemRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final FoodItemRepository foodItemRepository;

    public MealService(
            MealRepository mealRepository,
            FoodItemRepository foodItemRepository) {

        this.mealRepository = mealRepository;
        this.foodItemRepository = foodItemRepository;
    }

    // Add a meal
    public Meal addMeal(Meal meal) {

        FoodItem foodItem = foodItemRepository
                .findById(meal.getFoodItemId())
                .orElseThrow(() ->
                        new RuntimeException("Food item not found"));

        /*
         * FoodItem calories are considered per 100 grams.
         * Total calories are calculated based on quantity.
         */
        Double totalCalories =
                (foodItem.getCalories() * meal.getQuantity()) / 100;

        meal.setTotalCalories(totalCalories);

        if (meal.getMealDate() == null) {
            meal.setMealDate(LocalDate.now());
        }

        return mealRepository.save(meal);
    }

    // Get all meals
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    // Get meal by ID
    public Meal getMealById(Long id) {

        return mealRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Meal not found"));
    }

    // Get meals of a particular user
    public List<Meal> getMealsByUserId(Long userId) {

        return mealRepository.findByUserId(userId);
    }

    // Get meals of a user for a particular date
    public List<Meal> getMealsByUserIdAndDate(
            Long userId,
            LocalDate date) {

        return mealRepository.findByUserIdAndMealDate(
                userId,
                date
        );
    }

    // Update meal
    public Meal updateMeal(
            Long id,
            Meal updatedMeal) {

        Meal existingMeal =
                mealRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Meal not found"));

        FoodItem foodItem = foodItemRepository
                .findById(updatedMeal.getFoodItemId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Food item not found"));

        existingMeal.setUserId(updatedMeal.getUserId());
        existingMeal.setFoodItemId(
                updatedMeal.getFoodItemId());
        existingMeal.setMealType(
                updatedMeal.getMealType());
        existingMeal.setQuantity(
                updatedMeal.getQuantity());

        Double totalCalories =
                (foodItem.getCalories()
                        * updatedMeal.getQuantity()) / 100;

        existingMeal.setTotalCalories(totalCalories);

        existingMeal.setMealDate(
                updatedMeal.getMealDate());

        if (existingMeal.getMealDate() == null) {
            existingMeal.setMealDate(LocalDate.now());
        }

        return mealRepository.save(existingMeal);
    }

    // Delete meal
    public void deleteMeal(Long id) {

        if (!mealRepository.existsById(id)) {
            throw new RuntimeException("Meal not found");
        }

        mealRepository.deleteById(id);
    }
}