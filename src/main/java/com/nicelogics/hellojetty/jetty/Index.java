/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.jetty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("index.html")
public class Index {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {

        int port = 2222;
        String s = "<html>\r\n";

        s = addSection(s, "/hello");
        s = addLink(s, "/bye", "http://localhost:" + port + "/hello/bye");
        s = addLink(s, "/welcome?n=jetty", "http://localhost:" + port + "/hello/welcome?n=jetty");

        s = addSection(s, "/get");
        s = addLink(s, "/list", "http://localhost:" + port + "/get/list");
        s = addLink(s, "/student", "http://localhost:" + port + "/get/student");

        s = addSection(s, "/session");
        s = addLink(s, "/get", "http://localhost:" + port + "/session/get");
        s = addLink(s, "/set", "http://localhost:" + port + "/session/set?n=jetty");
        s = addLink(s, "/clear", "http://localhost:" + port + "/session/clear");

        s = addSection(s, "/jackson");
        s = addLink(s, "/test", "http://localhost:" + port + "/jackson/test");

        s = addSection(s, "/logback");
        s = addLink(s, "/test", "http://localhost:" + port + "/logback/test");

        s = addSection(s, "/orientdb");
        s = addLink(s, "/graph", "http://localhost:" + port + "/orientdb/graph");
        s = addLink(s, "/object", "http://localhost:" + port + "/orientdb/object");

        s += "</html>";
        return s;
    }

    private String addLink(String text, String label, String url) {
        return text + "<a href=\"" + url + "\" target=\"_blank\">" + label + "</a><br>\r\n";
    }

    private String addSection(String text, String label) {
        return text + "<h1>" + label + "</h1>\r\n";
    }
}
