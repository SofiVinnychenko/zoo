package com.app.tasks.controller;

import com.app.tasks.DTO.FeedingscheduleDTO;
import com.app.tasks.service.FeedingScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "feeding_schedule_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class FeedingscheduleController {

    private final FeedingScheduleService feedingScheduleService;

    @Operation(
            summary = "заносимо розклад в базу",
            description = "отримуємо DTO розкладу в сервісі, збираємо білдером, зберігаємо сутність у базу, " +
                    "викликаємо метод через контролер"
    )
    @PostMapping("/feedschedule/add")
    public void addFeed(@RequestBody FeedingscheduleDTO feedingScheduleDTO) {
        feedingScheduleService.createFeedingSchedule(feedingScheduleDTO);
    }
}
