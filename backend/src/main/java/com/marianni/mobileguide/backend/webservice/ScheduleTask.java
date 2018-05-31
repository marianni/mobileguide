package com.marianni.mobileguide.backend.webservice;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * @author mariannarachelova
 */
@Stateless
public class ScheduleTask {

    @Inject
    private HTMLParserFreeFood parserFreefood;

    @Inject
    private HTMLParserEmployees parserEmployee;

    @Inject
    private HTMLParserCandle parserCandle;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void runTask() {
        try {
            parserFreefood.parseFreefood();
            parserEmployee.parseAndCreateEmployees();
            parserCandle.parseCandle();


        } catch (Exception e) {

        }
    }

}
