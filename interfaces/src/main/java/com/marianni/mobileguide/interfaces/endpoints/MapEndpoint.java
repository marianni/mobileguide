package com.marianni.mobileguide.interfaces.endpoints;


import com.marianni.mobileguide.interfaces.dto.MapDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/faculty")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public interface MapEndpoint {

    @GET
    @Path("{id}")
    String getMap(@PathParam("id") Long id);

    @GET
    @Path("/all")
    Set<MapDTO> getMaps();

    @PUT
    @Path("")
    MapDTO update(MapDTO map);

    @POST
    @Path("")
    MapDTO create(MapDTO map);

    @DELETE
    @Path("{id}")
    void delete(@PathParam("id") Long id);
}
