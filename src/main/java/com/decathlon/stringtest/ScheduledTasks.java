package com.decathlon.stringtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 * * * * *")
    public void reportCurrentTime() {
        final String currentTime = dateFormat.format(new Date());
        log.info("The time is now {}", currentTime);
    }
}
