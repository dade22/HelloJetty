/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.erp;

import java.awt.Desktop;
import java.net.URI;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import com.nicelogics.hellojetty.utility.Logger;

/**
 *
 * @author davide
 */
public class Start {

    private final static String CLOGNAME = "Start";

    public static void main(String[] args) throws Exception {

        new Start().go(2278);
    }

    private void go(int port) throws Exception {

        boolean InDev = true;
        Server server = null;

        try {

            Logger.i(CLOGNAME, "go", "welcome");

            Logger.i(CLOGNAME, "go", "user.dir: " + System.getProperty("user.dir"));
            Logger.i(CLOGNAME, "go", "user.home: " + System.getProperty("user.home"));
            Logger.i(CLOGNAME, "go", "java.home: " + System.getProperty("java.home"));

            Logger.i(CLOGNAME, "go", "http://localhost:" + port + "/index.html");
            Logger.i(CLOGNAME, "go", "\r\n");

            ResourceConfig resourceConfig = new ResourceConfig();
            resourceConfig.packages("com.nicelogics.erp.rest");

            ServletContainer servletContainer = new ServletContainer(resourceConfig);
            ServletHolder sh = new ServletHolder(servletContainer);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            context.addServlet(sh, "/*");

            server = new Server(port);
            server.setHandler(context);
            server.setStopAtShutdown(true);
            server.setDumpBeforeStop(true);

            server.start();

            if (InDev && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:" + port + "/index.html"));
            }

            server.join();

        } catch (Exception ex) {

            ex.printStackTrace();
            throw ex;

        } finally {

            if (server != null) {
                server.destroy();
            }
        }

        Logger.i(CLOGNAME, "go", "bye!");
    }
}
