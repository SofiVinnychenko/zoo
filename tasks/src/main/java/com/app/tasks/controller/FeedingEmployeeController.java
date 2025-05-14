package com.app.tasks.controller;

import com.app.tasks.DTO.FeedingEmployeeDTO;
import com.app.tasks.entity.Employees;
import com.app.tasks.entity.FeedingSchedule;
import com.app.tasks.service.EmployeeService;
import com.app.tasks.service.FeedingScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "feeding_employee_methods (intermediate table)")
@Slf4j
@RestController
@RequiredArgsConstructor
public class FeedingEmployeeController {

    private final EmployeeService employeeService;
    private final FeedingScheduleService feedingScheduleService;

    @Operation(
            summary = "призначення робітника у розклад годування",
            description = "перевіряємо, чи є потрібні id у потрібних сутностях та створюємо рядок у позитивному випадку." +
                    "зберігаємо зміни за допомогою репозиторію"
    )
    @PostMapping("/feedemployee/assignments/assign")
    public ResponseEntity<String> assignEmployeeToFeeding(@RequestBody FeedingEmployeeDTO feedEmployeeDTO) {
        Employees employees = employeeService.findById(feedEmployeeDTO.getEmployeeId());
        FeedingSchedule feedingSchedule = feedingScheduleService.findById(feedEmployeeDTO.getFeedingscheduleId());

        feedingSchedule.getEmployees().add(employees);
        employees.getFeedingSchedules().add(feedingSchedule);

        feedingScheduleService.save(feedingSchedule);

        return ResponseEntity.ok("Employee assigned to feeding successfully");
    }

    @Operation(
            summary = "видалення робітника з розкладу годування",
            description = "перевіряємо, чи є потрібні id у проміжній таблиці та прибираємо рядок у позитивному випадку." +
                    "зберігаємо зміни за допомогою репозиторію"
    )
    @PostMapping("/feedemployee/assignments/unassign")
    public ResponseEntity<String> unassignEmployeeFromFeeding(@RequestBody FeedingEmployeeDTO feedEmployeeDTO) {
        Employees employees = employeeService.findById(feedEmployeeDTO.getEmployeeId());
        FeedingSchedule feedingSchedule = feedingScheduleService.findById(feedEmployeeDTO.getFeedingscheduleId());

        feedingSchedule.getEmployees().remove(employees);
        employees.getFeedingSchedules().remove(feedingSchedule);

        feedingScheduleService.save(feedingSchedule);

        return ResponseEntity.ok("Employee unassigned from feeding successfully");
    }

}
