package com.app.tasks.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedingFoodDTO {

    private Long feedingscheduleId;
    private Long foodId;
}
