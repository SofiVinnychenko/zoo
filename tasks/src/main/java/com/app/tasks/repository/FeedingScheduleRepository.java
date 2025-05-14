package com.app.tasks.repository;

import com.app.tasks.entity.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {
}
