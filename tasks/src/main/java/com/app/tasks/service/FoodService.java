package com.app.tasks.service;

import com.app.tasks.DTO.FoodDTO;
import com.app.tasks.entity.Food;
import com.app.tasks.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    // створення нового корму
    public void createFood(@RequestBody FoodDTO foodDTO) {
        foodRepository.save(Food.builder()
                .expirationDate(foodDTO.getExpirationDate())
                .title(foodDTO.getTitle())
                .storageConditions(foodDTO.getStorageConditions())
                .build());
    }

    // перевірка існування корму за id для проміжної таблиці
    public Food findById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
    }

    // збереження змін
    public Food save(Food food) {
        return foodRepository.save(food);
    }
}
