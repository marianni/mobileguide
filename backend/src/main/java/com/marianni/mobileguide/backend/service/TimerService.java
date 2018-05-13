package com.marianni.mobileguide.backend.service;

import com.marianni.mobileguide.backend.domain.Timer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TimerService {

    @PersistenceContext
    private EntityManager em;

    public Boolean isRunningUpdate(){
        Timer timer = em.find(Timer.class,1L);
        System.out.println("moja hodnota " + timer.getRunningUpdate());
        return timer.getRunningUpdate();
    }

    public void setTimerValue(Boolean value){
        Timer timer = new Timer();
        timer.setRunningUpdate(value);
    }
}
