package com.marianni.mobileguide.interfaces.endpoints;

import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
import com.marianni.mobileguide.interfaces.dto.CanteenDailyOfferDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/canteen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CanteenEndPoint{

    @GET
    @Path("{id}")
    String getCanteen(@PathParam("id") Long id);

    @GET
    @Path("import")
    String getAllMenus();

    @GET
    @Path("/all")
    Set<CanteenDTO> getAllCanteens();

    @GET
    @Path("/allMenu")
    Set<CanteenDailyOfferDTO> getCanteenDailyOffer();

    @POST
    @Path("")
    CanteenDTO create(CanteenDTO canteen);

    @PUT
    @Path("")
    CanteenDTO update(CanteenDTO canteen);

    @DELETE
    @Path("{id}")
    void delete(@PathParam("id") Long id);

    @PUT
    @Path("dailyOffer/")
    CanteenDailyOfferDTO updateDailyOffer(CanteenDailyOfferDTO dailyOfferDTO);

    @POST
    @Path("dailyOffer/")
    CanteenDailyOfferDTO createDailyOffer(CanteenDailyOfferDTO dailyOfferDTO);

    @DELETE
    @Path("dailyOffer/{dailyOfferId}")
    void deleteDailyOffer(@PathParam("dailyOfferId") Long dailyOfferId);
    }

