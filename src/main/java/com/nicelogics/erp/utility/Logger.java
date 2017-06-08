/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.erp.utility;

import org.slf4j.LoggerFactory;

/**
 * @author davide
 */
public class Logger {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    public static final void d(String caller, String method, String message) {

        String msg = "D[" + caller + "." + method + "] " + message;
        LOGGER.debug(msg);
        System.out.println(msg);
    }

    public static final void d(String caller, String method, String message, Throwable t) {

        String msg = "D[" + caller + "." + method + "] " + message;
        LOGGER.debug(msg, t);
        System.out.println(msg);
        if (t != null) {
            System.out.println(t.getMessage());
        }
    }

    public static final void i(String caller, String method, String message) {

        String msg = "I[" + caller + "." + method + "] " + message;
        LOGGER.info(msg);
        System.out.println(msg);
    }

    public static final void i(String caller, String method, String message, Throwable t) {

        String msg = "I[" + caller + "." + method + "] " + message;
        LOGGER.info(msg, t);
        System.out.println(msg);
        if (t != null) {
            System.out.println(t.getMessage());
        }
    }

    public static final void w(String caller, String method, String message) {

        String msg = "W[" + caller + "." + method + "] " + message;
        LOGGER.warn(msg);
        System.out.println(msg);
    }

    public static final void w(String caller, String method, String message, Throwable t) {

        String msg = "W[" + caller + "." + method + "] " + message;
        LOGGER.warn(msg, t);
        System.out.println(msg);
        if (t != null) {
            System.out.println(t.getMessage());
        }
    }

    public static final void e(String caller, String method, String message) {

        String msg = "E[" + caller + "." + method + "] " + message;
        LOGGER.error(msg);
        System.out.println(msg);
    }

    public static final void e(String caller, String method, String message, Throwable t) {

        String msg = "E[" + caller + "." + method + "] " + message;
        LOGGER.error(msg, t);
        System.out.println(msg);
        if (t != null) {
            System.out.println(t.getMessage());
        }
    }
}
