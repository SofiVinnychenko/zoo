package com.app.tasks.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnclosureDTO {
    String name;
    String type;
    int capacity;

}
