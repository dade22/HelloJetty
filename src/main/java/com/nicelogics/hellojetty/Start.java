/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty;

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

        new Start().go(2222);
    }

    private void go(int port) throws Exception {

        Server server = null;
        try {

            System.out.println("http://localhost:"+port+"/test/hello");
            System.out.println("http://localhost:"+port+"/welcome/welcome?n=jetty");
            System.out.println("\r\n");

            ResourceConfig resourceConfig = new ResourceConfig();
            resourceConfig.packages("com.nicelogics.hellojetty.resources");
//            resourceConfig.register(Calculator.class);

            ServletContainer servletContainer = new ServletContainer(resourceConfig);
            ServletHolder sh = new ServletHolder(servletContainer);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            context.addServlet(sh, "/*");

            server = new Server(port);
            server.setHandler(context);
            server.setStopAtShutdown(true);
            server.setDumpBeforeStop(true);

//        ResourceConfig config = new ResourceConfig();
//        config.packages("com.nicelogics.hellojetty.resources");
//        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
//        Server server = new Server(2222);
//        ServletContextHandler context = new ServletContextHandler(server, "/*");
//        context.addServlet(servlet, "/*");

            server.start();
            server.join();

        } catch (Exception ex) {

            throw ex;

        } finally {

            if (server != null) {
                server.destroy();
            }
        }
    }
}
