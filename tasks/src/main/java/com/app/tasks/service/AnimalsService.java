package com.app.tasks.service;

import com.app.tasks.DTO.AnimalsDTO;
import com.app.tasks.DTO.EmployeesDTO;
import com.app.tasks.entity.Animals;
import com.app.tasks.entity.Employees;
import com.app.tasks.entity.Enclosure;
import com.app.tasks.repository.AnimalsRepository;
import com.app.tasks.repository.EnclosureRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalsService {

    private final AnimalsRepository animalsRepository;
    private final EnclosureRepository enclosureRepository;

    // створення нової тварини
    public Animals createAnimal(AnimalsDTO animalsDTO) {
        Enclosure enclosure = enclosureRepository.findById(animalsDTO.getEnclosureId())
                .orElseThrow(() -> new RuntimeException("Enclosure not found"));

        Animals animal = Animals.builder()
                .age(animalsDTO.getAge())
                .name(animalsDTO.getName())
                .species(animalsDTO.getSpecies())
                .enclosure(enclosure)
                .build();
        return animalsRepository.save(animal);
    }

    // конвертація сутності тварини в DTO для методу getAnimalById
    private AnimalsDTO convertToDTOAnimalEmployee(Animals animal) {
        return AnimalsDTO.builder()
                .name(animal.getName())
                .species(animal.getSpecies())
                .age(animal.getAge())
                .enclosureId(animal.getEnclosure().getId())
                .employee(animal.getEnclosure().getEmployee().stream()
                        .map(this::convertToDTOE)
                        .collect(Collectors.toList()))
                .build();
    }

    // конвертація сутності працівника для convertToDTOAnimalEmployee
    private EmployeesDTO convertToDTOE(Employees employee) {
        return EmployeesDTO.builder()
                .name(employee.getName())
                .role(employee.getRole())
                .salary(employee.getSalary())
                .build();
    }

    // виведення тварини та закріплених за нею працівників
    public AnimalsDTO getAnimalandEmp(Long id) {

        Animals animal = animalsRepository.findByIdWithEmployees(id)
                .orElseThrow(() -> new EntityNotFoundException("Животное не найдено"));

        return convertToDTOAnimalEmployee(animal);
    }

    // універсальна конвертація тварини в DTO
    public AnimalsDTO convertToDTO(Animals animals) {
        return AnimalsDTO.builder()
                .name(animals.getName())
                .species(animals.getSpecies())
                .age(animals.getAge())
                .enclosureId(animals.getEnclosure().getId())
                .build();
    }

    // отримати всіх тварин в форматі DTO
    public List<AnimalsDTO> getAllAnimals() {
        return animalsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // оновити інформацію про тварину
    public AnimalsDTO updateAnimal(Long id, AnimalsDTO animalsDTO) {
        Animals animal = animalsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        Enclosure enclosure = enclosureRepository.findById(animalsDTO.getEnclosureId())
                .orElseThrow(() -> new RuntimeException("Enclosure not found"));

        animal.setName(animalsDTO.getName());
        animal.setSpecies(animalsDTO.getSpecies());
        animal.setAge(animalsDTO.getAge());
        animal.setEnclosure(enclosure);

        Animals updatedAnimal = animalsRepository.save(animal);

        return convertToDTO(updatedAnimal);
    }
}
