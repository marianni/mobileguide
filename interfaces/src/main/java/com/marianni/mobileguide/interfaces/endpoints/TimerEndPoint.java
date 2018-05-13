package com.marianni.mobileguide.interfaces.endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/timer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TimerEndPoint {

    @GET
    @Path("")
    boolean isRunningUpdate();

    @PUT
    @Path("value/")
    void setTimerValue(Boolean value);
}
