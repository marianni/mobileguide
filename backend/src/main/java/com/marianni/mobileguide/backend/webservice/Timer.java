package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.TimerService;
import com.marianni.mobileguide.interfaces.restclients.RestClients;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class Timer {

    @Inject
    ScheduleTask task;


    @Inject
    TimerService service;


    @Schedule(hour = "*/1", minute = "*", second = "*", info = "Every 1 hour timer")
    public void automaticallyScheduled() {
        //podmienka ze ak je isRunning true nech task run
        if(service.isRunningUpdate()) {
            task.runTask();
        }
        //else vyskocim z funkcie
        return;
    }


}
