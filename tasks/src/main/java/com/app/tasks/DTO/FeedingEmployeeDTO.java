package com.app.tasks.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedingEmployeeDTO {

    private Long feedingscheduleId;
    private Long employeeId;
}
