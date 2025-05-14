package com.app.tasks.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnimalsDTO {
    String name;
    String species;
    int age;
    Long enclosureId;
    List<EmployeesDTO> employee;
}
