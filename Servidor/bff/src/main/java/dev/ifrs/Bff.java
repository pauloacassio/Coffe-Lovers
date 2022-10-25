package dev.ifrs;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.Form;

import io.quarkus.vertx.http.runtime.devmode.Json;
import io.vertx.mutiny.ext.auth.User;

@Path("/bff")
public class Bff {

    /** ----------- USER ----------- */
    @Inject
    @RestClient
    UserClient user;

    @POST
    @Path("/user/save")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String save(@FormParam("name") String name, @FormParam("login") String login, @FormParam("senha") String senha) {
        return user.save(name, login, senha);
    }
    
    @GET
    @Path("/user/list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String listUser() {
        return user.list();
    }

    @GET
    @Path("/user/list/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String listUser(@PathParam("id") Long id) {
        return user.find(id);
    }
}