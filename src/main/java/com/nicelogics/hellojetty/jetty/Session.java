/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.jetty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 * @author Developer
 */
@Path("/session")
public class Session {

    @Context
    private HttpServletRequest request;

    @GET
    @Path("get")
    public String get() {

        HttpSession session = request.getSession();
        return "time:" + session.getLastAccessedTime() + " n:" + session.getAttribute("n");
    }

    @GET
    @Path("set")
    public String set(@QueryParam("n") String name) {

        HttpSession session = request.getSession();
        //String existingName = (String)session.getAttribute("n");
        //if (existingName == null || existingName.trim().length() == 0)
        session.setAttribute("n", name);
        return "time:" + session.getLastAccessedTime() + " ok";
    }

    @GET
    @Path("clear")
    public String clear() {

        HttpSession session = request.getSession();
        session.setAttribute("n", null);
        return "time:" + session.getLastAccessedTime() + " cleared";
    }
}
