/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.jetty;

import com.nicelogics.hellojetty.logback.HaphazardLogger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("logback")
public class Logback {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String bye() {

        new HaphazardLogger().go();
        return "ok, check logs";
    }
}
