package com.marianni.mobileguide.interfaces.endpoints;

import com.marianni.mobileguide.interfaces.dto.MobileDataDTOV1;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/mobiledata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface MobileDataEndPoint {

    @GET
    @Path("/v1/{latestDataVersion}")
    Set<MobileDataDTOV1> getMobileDataV1(@PathParam("latestDataVersion") Long latestDataVersion);
}
