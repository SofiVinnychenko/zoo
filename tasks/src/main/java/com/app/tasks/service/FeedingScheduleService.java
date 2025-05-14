package com.app.tasks.service;

import com.app.tasks.DTO.FeedingscheduleDTO;
import com.app.tasks.entity.Enclosure;
import com.app.tasks.entity.FeedingSchedule;
import com.app.tasks.repository.EnclosureRepository;
import com.app.tasks.repository.FeedingScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedingScheduleService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final EnclosureRepository enclosureRepository;

    // створення нового розкладу
    public FeedingSchedule createFeedingSchedule(FeedingscheduleDTO feedingscheduleDTO) {
        Enclosure enclosure = enclosureRepository.findById(feedingscheduleDTO.getEncloseId())
                .orElseThrow(() -> new RuntimeException("Enclosure not found"));

        FeedingSchedule feedingSchedule = FeedingSchedule.builder()
                .timefeed(feedingscheduleDTO.getTimefeed())
                .enclose(enclosure)
                .build();
        return feedingScheduleRepository.save(feedingSchedule);
    }

    // перевірка існування розкладу за id для проміжної таблиці
    public FeedingSchedule findById(Long id) {
        return feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feeding not found"));
    }

    // збереження змін
    public FeedingSchedule save(FeedingSchedule feedingSchedule) {
        return feedingScheduleRepository.save(feedingSchedule);
    }
}
