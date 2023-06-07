package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CRUD {
    public void addMeal(LocalDateTime dateTime, String description, int calories);
    public void updateMeal(Integer id, LocalDateTime dateTime, String description, int calories);
    public void deleteMeal(Integer id);
    public Meal getMealByID(Integer id);
    public List<Meal> getAllMeals();
}
