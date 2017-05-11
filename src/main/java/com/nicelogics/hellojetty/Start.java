/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty;

import com.nicelogics.hellojetty.resources.Calculator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 *
 * @author davide
 */
public class Start {

    public static void main(String[] args) throws Exception {
        
        new Start().go();
    }

    public void go() throws Exception {

        Server server = null;
        try {

            System.out.println("http://localhost:2222/home/hello");
            System.out.println("http://localhost:2222/calculator/square?input=16");
            System.out.println("http://localhost:2222/calculator/squareRoot?input=16");
            System.out.println("\r\n");

            server = configureServer();
            server.start();
            //server.dumpStdErr();
            server.join();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            if (server != null)
                server.destroy();
        }
    }

    private Server configureServer() {

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("com.nicelogics.hellojetty.resources");
        resourceConfig.register(Calculator.class);

        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder sh = new ServletHolder(servletContainer);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(sh, "/*");

        Server server = new Server(2222);
        server.setHandler(context);
        
//        ResourceConfig config = new ResourceConfig();
//        config.packages("com.nicelogics.hellojetty.resources");
//        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
//        Server server = new Server(2222);
//        ServletContextHandler context = new ServletContextHandler(server, "/*");
//        context.addServlet(servlet, "/*");
        
        return server;
    }
}
