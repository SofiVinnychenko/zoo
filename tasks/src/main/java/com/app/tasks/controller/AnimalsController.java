package com.app.tasks.controller;

import com.app.tasks.DTO.AnimalsDTO;
import com.app.tasks.service.AnimalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "animal_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class AnimalsController {

    private final AnimalsService animalsService;

    @Operation(
            summary = "виведення тварини та її працівників з іншої таблиці (через зв'язок з вольєром)",
            description = "конвертуємо сутність тварини (і працівника) в DTO у сервісі, виводимо за допомогою методу" +
                    "(викликаємо через контролер)"
    )
    @GetMapping("/employee/animals/{id}")
    public ResponseEntity<AnimalsDTO> getAnimals(@PathVariable Long id) {
        return ResponseEntity.ok(animalsService.getAnimalandEmp(id));
    }

    @Operation(
            summary = "заносимо тварину в базу",
            description = "отримуємо DTO тварини в сервісі, збираємо білдером, зберігаємо сутність в базу, " +
                    "викликаємо метод через контролер"
    )
    @PostMapping("/animal/add")
    public void addAnimals(@RequestBody AnimalsDTO animalsDTO) {
        animalsService.createAnimal(animalsDTO);
    }

    @Operation(
            summary = "виведення всіх тварин",
            description = "отримуємо DTO тварини в сервісі, викликаємо метод через контролер"
    )
    @SneakyThrows
    @GetMapping("/animals/all")
    public List<AnimalsDTO> getAll() {
        return animalsService.getAllAnimals();
    }

    @Operation(
            summary = "зміна даних про тварину",
            description = "у сервісі перевіряємо існування id, за допомогою Set встановлюємо потрібні значення, де треба"
    )
    @PutMapping("/animal/update/{id}")
    public AnimalsDTO changeAnimal(@PathVariable Long id, @RequestBody AnimalsDTO animalsDTO) {
        return animalsService.updateAnimal(id, animalsDTO);
    }
}

