package com.marianni.mobileguide.interfaces.restclients;

import com.marianni.mobileguide.interfaces.endpoints.*;

public class RestClients {
    public static final String DEFAULT_URL = "http://mariannas-macbook-pro.local:8080/backend/rest/";

    public static EmployeeEndpoint employee(){
        return RestClientGenerator.generateClient(EmployeeEndpoint.class, DEFAULT_URL);
    }

    public static CanteenEndPoint canteen(){
        return RestClientGenerator.generateClient(CanteenEndPoint.class,DEFAULT_URL);
    }

    public static CandleEndPoint candle(){
        return RestClientGenerator.generateClient(CandleEndPoint.class,DEFAULT_URL);
    }

    public static TimerEndPoint timer(){
        return RestClientGenerator.generateClient(TimerEndPoint.class,DEFAULT_URL);
    }

    public static MapEndpoint map() { return RestClientGenerator.generateClient(MapEndpoint.class,DEFAULT_URL);}
}
