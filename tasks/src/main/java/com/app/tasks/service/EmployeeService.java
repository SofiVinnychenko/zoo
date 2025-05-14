package com.app.tasks.service;

import com.app.tasks.DTO.EmployeesDTO;
import com.app.tasks.entity.Employees;
import com.app.tasks.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeesRepository employeesRepository;

    // створення нового робітника
    public void createEmployee(@RequestBody EmployeesDTO employeesDTO) {
        employeesRepository.save(Employees.builder()
                        .name(employeesDTO.getName())
                        .role(employeesDTO.getRole())
                        .salary(employeesDTO.getSalary())
                        .build());
    }

    // конвертація в DTO для методів GET
    public EmployeesDTO convertToDTOforGet(Employees employees) {
        return EmployeesDTO.builder()
                .name(employees.getName())
                .role(employees.getRole())
                .salary(employees.getSalary())
                .build();
    }

    // отримати робітника за його роллю
    public List<EmployeesDTO> getEmployeesByRole(String role) {
        List<Employees> employeesList = employeesRepository.findAllByRole(role);
        if (employeesList.isEmpty()) {
            throw new RuntimeException("Employees not found");
        }
        return employeesList.stream()
                .map(this::convertToDTOforGet)
                .collect(Collectors.toList());
    }

    // перевірка існування робітника за id для проміжної таблиці
    public Employees findById(Long id) {
        return employeesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employees not found"));
    }

    // збереження змін
    public Employees save(Employees employees) {
        return employeesRepository.save(employees);
    }
}
