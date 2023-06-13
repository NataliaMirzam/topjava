package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import static ru.javawebinar.topjava.util.MealsUtil.*;
import static ru.javawebinar.topjava.web.SecurityUtil.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealService service;

    public Collection<MealTo> getAll() {
        log.info("getAll");
        return getTos(service.getAll(authUserId()), authUserCaloriesPerDay());
    }

    public Collection<MealTo> getAllBetweenTimes(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        log.info("getAllBetweenTimes");
        return getFilteredTos(service.getAll(authUserId()), authUserCaloriesPerDay(), startDateTime, endDateTime, LocalTime.class);
    }

    public Collection<MealTo> getAllBetweenDates(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        log.info("getAllBetweenDates");
        return getFilteredTos(service.getAll(authUserId()), authUserCaloriesPerDay(), startDateTime, endDateTime, LocalDate.class);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public void update(Meal meal) {
        log.info("update {} with id={}", meal, meal.getId());
        service.update(meal, authUserId());
    }
}