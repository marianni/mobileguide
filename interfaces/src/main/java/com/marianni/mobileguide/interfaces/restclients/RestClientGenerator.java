package com.marianni.mobileguide.interfaces.restclients;


import org.glassfish.jersey.client.proxy.WebResourceFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class RestClientGenerator {

    public static <T> T generateClient(Class<T> clazz, String defaultUrl){
        WebTarget t = ClientBuilder.newClient().target(defaultUrl);
        T resource = WebResourceFactory.newResource(clazz, t);
        return resource;
    }

}
