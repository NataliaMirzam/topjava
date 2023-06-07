package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class CRUDCollection implements CRUD{

    @Override
    public void addMeal(LocalDateTime dateTime, String description, int calories) {
        MealServlet.meals.add(new Meal(dateTime, description, calories));
    }

    @Override
    public void updateMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        for (Meal meal: MealServlet.meals) {
            if (meal.getId() == id) {
                meal.setDateTime(dateTime);
                meal.setDescription(description);
                meal.setCalories(calories);
                return;
            }
        }
    }

    @Override
    public void deleteMeal(Integer id) {
        for (int i = 0; i < MealServlet.meals.size(); i++) {
            if (MealServlet.meals.get(i).getId() == id) {
                MealServlet.meals.remove(i);
                return;
            }
        }
    }

    @Override
    public Meal getMealByID(Integer id) {
        return MealServlet.meals.get(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        return MealServlet.meals;
    }
}
