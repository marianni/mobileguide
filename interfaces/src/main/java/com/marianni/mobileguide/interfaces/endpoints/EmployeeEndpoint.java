package com.marianni.mobileguide.interfaces.endpoints;

import com.marianni.mobileguide.interfaces.dto.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface EmployeeEndpoint {

    @GET
    @Path("{id}")
    String getEmployee(@PathParam("id") Long id);

    @GET
    @Path("import")
    String importEmployees();

    @GET
    @Path("/all")
    Set<EmployeeDTO> getEmployees();

    @PUT
    @Path("")
    EmployeeDTO update(EmployeeDTO employee);

    @POST
    @Path("")
    EmployeeDTO create(EmployeeDTO employee);

    @DELETE
    @Path("{id}")
    void delete(@PathParam("id") Long id);

    @DELETE
    @Path("phoneNumber/{phoneNumberId}")
    void deletePhoneNumber(@PathParam("phoneNumberId") Long phoneNumberId);

    @DELETE
    @Path("publication/{publicationId}")
    void deletePublication(@PathParam("publicationId") Long publicationId);

    @DELETE
    @Path("voip/{voipId}")
    void deleteVoip(@PathParam("voipId") Long voipId);

    @DELETE
    @Path("web/{webId}")
    void deleteWeb(@PathParam("webId") Long webId);

    @DELETE
    @Path("place/{placeId}")
    void deletePlace(@PathParam("placeId") Long placeId);

    @PUT
    @Path("place/")
    PlaceDTO updatePlace(PlaceDTO placeDTO);

    @POST
    @Path("place/")
    PlaceDTO createPlace(PlaceDTO placeDTO);

    @PUT
    @Path("phoneNumber/")
    PhoneNumberDTO updatePhoneNumber(PhoneNumberDTO dto);

    @POST
    @Path("/phoneNumber")
    PhoneNumberDTO createPhoneNumber(PhoneNumberDTO dto);

    @PUT
    @Path("/publication")
    PublicationDTO updatePublication(PublicationDTO dto);

    @POST
    @Path("/publication")
    PublicationDTO createPublication(PublicationDTO dto);

    @PUT
    @Path("/voip")
    VoipDTO updateVoip(VoipDTO dto);

    @POST
    @Path("/voip")
    VoipDTO createVoip(VoipDTO dto);

    @PUT
    @Path("/web")
    WebDTO updateWeb(WebDTO dto);

    @POST
    @Path("/web")
    WebDTO createWeb(WebDTO dto);
}