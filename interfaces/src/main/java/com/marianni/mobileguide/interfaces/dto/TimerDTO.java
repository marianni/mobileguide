package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

/**
 * @author mariannarachelova on 27/05/2018
 */
public class TimerDTO implements Serializable {

    private Boolean isRunning;

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }
}
