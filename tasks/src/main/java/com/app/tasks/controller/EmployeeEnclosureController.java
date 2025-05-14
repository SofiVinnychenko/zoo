package com.app.tasks.controller;

import com.app.tasks.DTO.EmployeeEnclosureDTO;
import com.app.tasks.entity.Employees;
import com.app.tasks.entity.Enclosure;
import com.app.tasks.service.EmployeeService;
import com.app.tasks.service.EnclosureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "employee_enclosure_methods (intermediate table)")
@RestController
@RequiredArgsConstructor
public class EmployeeEnclosureController {

    private final EmployeeService employeeService;
    private final EnclosureService enclosureService;

    @Operation(
            summary = "призначення працівника у вольєр",
            description = "перевіряємо, чи є потрібні id у потрібних сутностях та створюємо рядок у позитивному випадку." +
                    "зберігаємо зміни за допомогою репозиторію"
    )
    @PostMapping("/enclosemployee/assignments/assign")
    public ResponseEntity<String> assignEmployeeToEnclosure(@RequestBody EmployeeEnclosureDTO assignmentDTO) {
        Employees employees = employeeService.findById(assignmentDTO.getEmployeesId());
        Enclosure enclosure = enclosureService.findById(assignmentDTO.getEnclosureId());

        enclosure.getEmployee().add(employees);
        employees.getEnclosures().add(enclosure);

        enclosureService.save(enclosure);

        return ResponseEntity.ok("Employee assigned to enclosure successfully");
    }

    @Operation(
            summary = "переведення працівника з вольєру",
            description = "перевіряємо, чи є потрібні id у проміжній таблиці та прибираємо рядок у позитивному випадку." +
                    "зберігаємо зміни за допомогою репозиторію"
    )
    @PostMapping("/enclosemployee/assignments/unassign")
    public ResponseEntity<String> unassignEmployeeFromEnclosure(@RequestBody EmployeeEnclosureDTO assignmentDTO) {
        Employees employees = employeeService.findById(assignmentDTO.getEmployeesId());
        Enclosure enclosure = enclosureService.findById(assignmentDTO.getEnclosureId());

        enclosure.getEmployee().remove(employees);
        employees.getEnclosures().remove(enclosure);

        enclosureService.save(enclosure);

        return ResponseEntity.ok("Employee unassigned from enclosure successfully");
    }
}
