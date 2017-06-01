/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.jetty;

import com.nicelogics.hellojetty.structures.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("get")
public class Retrieve {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> list() {

        List<String> l = new ArrayList<>();
        l.add("one");
        l.add("two");
        l.add("three");
        return l;
    }

    @GET
    @Path("student")
    @Produces(MediaType.APPLICATION_JSON)
    public Student student() {

        return new Student();
    }
}
