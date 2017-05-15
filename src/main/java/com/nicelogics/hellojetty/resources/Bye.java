/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("home")
public class Bye {

    @GET
    @Path("bye")
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld() {

        return "Bye, world!";
    }
}