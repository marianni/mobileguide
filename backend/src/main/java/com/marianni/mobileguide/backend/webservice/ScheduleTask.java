package com.marianni.mobileguide.backend.webservice;


import com.marianni.mobileguide.backend.service.TimerService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mariannarachelova
 */
@Stateless
public class ScheduleTask {
    private final static Logger LOG = Logger.getLogger(ScheduleTask.class.getName());

    @Inject
    private HTMLParserFreeFood parserFreefood;

    @Inject
    private HTMLParserEmployees parserEmployee;

    @Inject
    private HTMLParserCandle parserCandle;

    @Inject
    private TimerService timerService;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void runTask() {
        try {
            if (timerService.shouldSynchronizeFromExternalSource()) {
                LOG.log(Level.SEVERE, "Running external sync now");
                parserFreefood.parseFreefood();
                parserEmployee.parseAndCreateEmployees();
                parserCandle.parseCandle();
                return;
            }
            LOG.log(Level.SEVERE, "External sync not running");

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Exception occured during Scheduler run");
        }
    }

}
