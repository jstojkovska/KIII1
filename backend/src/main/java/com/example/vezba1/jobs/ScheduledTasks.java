package com.example.vezba1.jobs;

import com.example.vezba1.service.domain.AccommodationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final AccommodationService accommodationService;

    public ScheduledTasks(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Scheduled(cron = "0 0 0 * * *") // sekoj den vo 00:00
    public void refreshMaterializedView(){
        accommodationService.refreshMaterializedView();
    }
}
