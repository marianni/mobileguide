package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.TimerService;
import com.marianni.mobileguide.interfaces.endpoints.TimerEndPoint;

import javax.inject.Inject;

public class TimerEndPointImpl implements TimerEndPoint {

    @Inject
    private TimerService service;

    @Override
    public boolean isRunningUpdate() {
        return service.isRunningUpdate();
    }

    @Override
    public void setTimerValue(Boolean value){ service.setTimerValue(value);}
}
