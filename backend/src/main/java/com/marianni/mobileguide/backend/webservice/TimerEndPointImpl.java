package com.marianni.mobileguide.backend.webservice;

import com.marianni.mobileguide.backend.service.TimerService;
import com.marianni.mobileguide.interfaces.dto.TimerDTO;
import com.marianni.mobileguide.interfaces.endpoints.TimerEndPoint;

import javax.inject.Inject;
/**
 * @author mariannarachelova
 */
public class TimerEndPointImpl implements TimerEndPoint {

    @Inject
    private TimerService service;

    @Override
    public TimerDTO isRunningUpdate() {
        TimerDTO dto = new TimerDTO();
        dto.setRunning(service.isRunningUpdate());
        return dto;
    }

    @Override
    public void setTimerValue(TimerDTO dto) {
        service.setTimerValue(dto.getRunning());
    }
}
