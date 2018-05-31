package com.marianni.mobileguide.backend.service;

import com.marianni.mobileguide.backend.domain.Timer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * @author mariannarachelova
 */
@Stateless
public class TimerService {

    @PersistenceContext
    private EntityManager em;

    public Boolean shouldSynchronizeFromExternalSource(){
        Timer timer = em.find(Timer.class,1L);
        return timer.getRunningUpdate();
    }

    public void setTimerValue(Boolean value){
        Timer timer = em.find(Timer.class,1L);
        timer.setRunningUpdate(value);
    }
}
