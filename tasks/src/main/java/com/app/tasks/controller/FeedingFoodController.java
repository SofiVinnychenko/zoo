package com.app.tasks.controller;

import com.app.tasks.DTO.FeedingFoodDTO;
import com.app.tasks.entity.FeedingSchedule;
import com.app.tasks.entity.Food;
import com.app.tasks.service.FeedingScheduleService;
import com.app.tasks.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "feeding_food_methods (intermediate table)")
@Slf4j
@RestController
@RequiredArgsConstructor
public class FeedingFoodController {

    private final FoodService foodService;
    private final FeedingScheduleService feedingScheduleService;

    @Operation(
            summary = "призначення корму в розклад годування",
            description = "перевіряємо, чи є потрібні id у потрібних сутностях та створюємо рядок у позитивному випадку." +
                    "зберігаємо зміни за допомогою репозиторію"
    )
    @PostMapping("/feedfood/assignments/assign")
    public ResponseEntity<String> assignFoodToFeeding(@RequestBody FeedingFoodDTO feedingFoodDTO) {
        Food food = foodService.findById(feedingFoodDTO.getFoodId());
        FeedingSchedule feedingSchedule = feedingScheduleService.findById(feedingFoodDTO.getFeedingscheduleId());

        feedingSchedule.getFoods().add(food);
        food.getFeedingSchedules().add(feedingSchedule);

        feedingScheduleService.save(feedingSchedule);

        return ResponseEntity.ok("Food assigned to feeding successfully");
    }

    @Operation(
            summary = "видалення корму з розкладу годування",
            description = "перевіряємо, чи є потрібні id у проміжній таблиці та прибираємо рядок у позитивному випадку." +
                    "зберігаємо зміни за допомогою репозиторію"
    )
    @PostMapping("/feedfood/assignments/unassign")
    public ResponseEntity<String> unassignFoodFromFeeding(@RequestBody FeedingFoodDTO feedingFoodDTO) {
        Food food = foodService.findById(feedingFoodDTO.getFoodId());
        FeedingSchedule feedingSchedule = feedingScheduleService.findById(feedingFoodDTO.getFeedingscheduleId());

        feedingSchedule.getFoods().remove(food);
        food.getFeedingSchedules().remove(feedingSchedule);

        feedingScheduleService.save(feedingSchedule);

        return ResponseEntity.ok("Food unassigned from feeding successfully");
    }

    @Operation(
            summary = "пошук розкладу за id корму",
            description = "метод отримує об'єкт Food по його ID через foodService. " +
                    "Розклади, пов'язані з їжею, перетворюються у DTO. Метод повертає список"
    )
    @GetMapping("/feedfood/byFood")
    public List<FeedingFoodDTO> getFeedingsByFood(@RequestParam Long foodId) {
        Food food = foodService.findById(foodId);

        return food.getFeedingSchedules().stream()
                .map(schedule -> {
                    FeedingFoodDTO dto = new FeedingFoodDTO();
                    dto.setFoodId(foodId);
                    dto.setFeedingscheduleId(schedule.getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "пошук корму по id розкладу",
            description = "метод отримує об'єкт feedingSchedule по його ID через feedingScheduleService. " +
                    "Розклади, пов'язані з їжею, перетворюються у DTO. Метод повертає список"
    )
    @GetMapping("/feedfood/byFeeding")
    public List<FeedingFoodDTO> getFoodsByFeeding(@RequestParam Long feedingId) {
        FeedingSchedule feedingSchedule = feedingScheduleService.findById(feedingId);

        return feedingSchedule.getFoods().stream()
                .map(food -> {
                    FeedingFoodDTO dto = new FeedingFoodDTO();
                    dto.setFeedingscheduleId(feedingId);
                    dto.setFoodId(food.getId());
                    return dto;
                })
                .collect(Collectors.toList());

    }
}
