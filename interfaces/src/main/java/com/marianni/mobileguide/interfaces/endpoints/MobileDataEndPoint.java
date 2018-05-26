package com.marianni.mobileguide.interfaces.endpoints;

import com.marianni.mobileguide.interfaces.dto.MobileDataDTOV1;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/mobiledata")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public interface MobileDataEndPoint {

    @GET
    @Path("/{modelVersion}/{latestDataVersion}")
    String getMobileDataV1(@PathParam("modelVersion") Long modelVersion, @PathParam("latestDataVersion") Long latestDataVersion);
}
