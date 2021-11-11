package com.gtss.mnp.ScheduledService;


import com.gtss.mnp.service.PortingRequestService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Configuration
@EnableScheduling
@AllArgsConstructor
@Service
public class SchedulerConfig {

    private final PortingRequestService portingRequestService;

    @Scheduled(fixedDelay = 120000)
    public void scheduledRequestUpdate() {

        portingRequestService.cancelPendingRequestsCreatedAfterTwoMinutes();
    }
}
