package com.marianni.mobileguide.interfaces.endpoints;

import com.marianni.mobileguide.interfaces.dto.TimerDTO;

import javax.ws.rs.*;
import java.sql.Time;

@Path("/timer")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public interface TimerEndPoint {

    @GET
    @Path("")
    TimerDTO shouldSynchronizeFromExternalSource();

    @PUT
    @Path("value/")
    void setTimerValue(TimerDTO value);
}
