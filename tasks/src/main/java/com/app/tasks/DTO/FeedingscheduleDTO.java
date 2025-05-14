package com.app.tasks.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Time;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedingscheduleDTO {

    Time timefeed;
    Long encloseId;
}
