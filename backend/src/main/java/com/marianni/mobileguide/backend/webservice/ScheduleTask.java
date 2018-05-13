package com.marianni.mobileguide.backend.webservice;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
public class ScheduleTask {

    @Inject
    HTMLParserEmployees parserEmployee;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void runTask(){
        try {
            parserEmployee.parseAndCreateEmployees();
        }
        catch (Exception e){
            
        }
    }
}
