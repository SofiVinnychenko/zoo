package com.app.tasks.service;

import com.app.tasks.DTO.EnclosureDTO;
import com.app.tasks.entity.Enclosure;
import com.app.tasks.repository.EnclosureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class EnclosureService {

    private final EnclosureRepository enclosureRepository;

    // створення нового вольєру
    public void createEnclosure(@RequestBody EnclosureDTO enclosureDTO) {
        enclosureRepository.save(Enclosure.builder()
                .capacity(enclosureDTO.getCapacity())
                .name(enclosureDTO.getName())
                .type(enclosureDTO.getType())
                .build());
    }

    // перевірка існування вольєру за id для проміжної таблиці
    public Enclosure findById(Long id) {
        return enclosureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enclosure not found"));
    }

    // збереження змін
    public Enclosure save(Enclosure enclosure) {
        return enclosureRepository.save(enclosure);
    }

}
