package com.app.tasks.controller;

import com.app.tasks.DTO.FoodDTO;
import com.app.tasks.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "food_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @Operation(
            summary = "заносимо корм в базу",
            description = "отримуємо DTO корму в сервісі, збираємо білдером, зберігаємо сутність у базу, " +
                    "викликаємо метод через контролер"
    )
    @PostMapping("/food/add")
    public void addFood(@RequestBody FoodDTO foodDTO) { foodService.createFood(foodDTO);
    }
}
