package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.TimerService;
import com.marianni.mobileguide.interfaces.restclients.RestClients;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * @author mariannarachelova
 */

@Startup
@Singleton
public class TimerScheduler {

    @Inject
    ScheduleTask task;


    @Inject
    TimerService service;


    @Schedule(hour = "*/1", minute = "*/2", info = "Every 1 hour timer")
    public void automaticallyScheduled() {
        if (service.isRunningUpdate()) {
            task.runTask();
        }
        else {
            return;
        }
    }


}
