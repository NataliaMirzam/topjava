package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
@RequestMapping("/meals")
public class JspMealController {
    private static final Logger log = LoggerFactory.getLogger(JspMealController.class);

    @Autowired
    private MealService service;

    @GetMapping()
    public String getAll(HttpServletRequest request, Model model) {
        if (request.getParameter("action") == null) {
            int userId = SecurityUtil.authUserId();
            log.info("getAll for user {}", userId);
            model.addAttribute("meals",
                    MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay()));
        } else {
            LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
            LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
            LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
            LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
            int userId = SecurityUtil.authUserId();
            log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

            List<Meal> mealsDateFiltered = service.getBetweenInclusive(startDate, endDate, userId);
            model.addAttribute("meals",
                    MealsUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime));
        }
        return "meals";
    }

    @GetMapping("/new")
    public String newMeal(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping()
    public String save(HttpServletRequest request, Model model) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            int userId = SecurityUtil.authUserId();
            assureIdConsistent(meal, getId(request));
            log.info("update {} for user {}", meal, userId);
            service.update(meal, userId);
        } else {
            int userId = SecurityUtil.authUserId();
            checkNew(meal);
            log.info("create {} for user {}", meal, userId);
            service.create(meal, userId);
        }
        return "redirect:/meals";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        int userId = SecurityUtil.authUserId();
        Meal meal = service.get(id, userId);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id, Model model) {
        int userId = SecurityUtil.authUserId();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
