package com.marianni.mobileguide.backend.webservice;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)

public class HelloEndPoint {

    @GET
    @Path("test")
    public String test() {
       return "Hello Marianni";
    }

    @GET
    @Path("test2")
    public String test2(){
        return "cauko kakavko";
    }

}
