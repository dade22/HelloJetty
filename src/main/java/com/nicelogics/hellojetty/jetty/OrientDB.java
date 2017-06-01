/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.jetty;

import com.nicelogics.hellojetty.orientdb.EmbeddedGraphDatabase;
import com.nicelogics.hellojetty.orientdb.EmbeddedObjectDatabase;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("orientdb")
public class OrientDB {

    @GET
    @Path("object")
    @Produces(MediaType.TEXT_PLAIN)
    public String object() {

        new EmbeddedObjectDatabase().go();
        return "done";
    }

    @GET
    @Path("graph")
    @Produces(MediaType.TEXT_PLAIN)
    public String graph() {

        new EmbeddedGraphDatabase().go();
        return "done";
    }
}
