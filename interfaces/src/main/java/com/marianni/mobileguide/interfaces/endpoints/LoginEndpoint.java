package com.marianni.mobileguide.interfaces.endpoints;

import com.marianni.mobileguide.interfaces.dto.LoginDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author mariannarachelova on 27/05/2018
 */

@Path("/login")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public interface LoginEndpoint {


    @POST
    @Path("")
    LoginDTO login(LoginDTO loginDto);
}
