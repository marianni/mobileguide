package com.marianni.mobileguide.interfaces.endpoints;


import com.marianni.mobileguide.interfaces.dto.CandlePlaceDTO;
import com.marianni.mobileguide.interfaces.dto.LectureDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/candle")
//@Produces(MediaType.APPLICATION_JSON)
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public interface CandleEndPoint {

    @GET
    @Path("{id}")
    String getCandlePlace(@PathParam("id") Long id);

    @GET
    @Path("import")
    String importCandlePlaces();

    @GET
    @Path("/all")
    Set<CandlePlaceDTO> getAllPlaces();

    /*
    @GET
    @Path("/allLectures")
    Set<LectureDTO> getCandleLectures();
    */
}
