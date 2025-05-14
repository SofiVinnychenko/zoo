package com.app.tasks.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodDTO {

    String title;
    Date expirationDate;
    String storageConditions;
}
