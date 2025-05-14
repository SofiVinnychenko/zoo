package com.app.tasks.controller;

import com.app.tasks.DTO.EmployeesDTO;
import com.app.tasks.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "employees_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeeService employeeService;

    @Operation(
            summary = "заносимо робітника в базу",
            description = "отримуємо DTO працівника в сервісі, збираємо білдером, зберігаємо сутність у базу, " +
                    "викликаємо метод через контролер"
    )
    @PostMapping("/employee/add")
    public void addEmployee(@RequestBody EmployeesDTO employeesDTO) { employeeService.createEmployee(employeesDTO); }

    @Operation(
            summary = "виведення всіх працівників за вказаною роллю",
            description = "отримуємо DTO працівника у сервісі, за вказаною роллю (метод прописаний у репозиторії) " +
                    "виводимо відповідних працівників"
    )
    @GetMapping("/employee")
    public List<EmployeesDTO> getAll(@RequestParam String role) {
        return employeeService.getEmployeesByRole(role);
    }
}
