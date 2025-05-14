package com.app.tasks.controller;

import com.app.tasks.DTO.EnclosureDTO;
import com.app.tasks.service.EnclosureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "enclosure_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class EnclosureController {

    private final EnclosureService enclosureService;

    @Operation(
            summary = "заносимо вольєр в базу",
            description = "отримуємо DTO вольєра в сервісі, збираємо білдером, зберігаємо сутність у базу, " +
                    "викликаємо метод через контролер"
    )
    @PostMapping("/enclosure/add")
    public void addEnclosure(@RequestBody EnclosureDTO enclosureDTO) {
        enclosureService.createEnclosure(enclosureDTO);
    }
}
