package com.app.tasks.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeesDTO {
    String name;
    String role;
    int salary;
}
