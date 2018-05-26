package com.marianni.mobileguide.interfaces.endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/timer")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public interface TimerEndPoint {

    @GET
    @Path("")
    boolean isRunningUpdate();

    @PUT
    @Path("value/")
    void setTimerValue(Boolean value);
}
