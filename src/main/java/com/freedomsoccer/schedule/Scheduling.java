package com.freedomsoccer.schedule;

import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Component
@Log4j
public class Scheduling {

    private static final SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "*/10 * * * * *")
    public void reportCurrentTime() {
        log.info("The time is now " + date.format(new Date()));
    }
}
