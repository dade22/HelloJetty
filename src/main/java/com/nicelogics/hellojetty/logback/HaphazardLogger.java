/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import com.nicelogics.hellojetty.utility.ResolveStream;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author davide
 */
public class HaphazardLogger {

    private static final Logger logger = LoggerFactory.getLogger(HaphazardLogger.class);

    public void go() {

        // applica logback.xml
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();
        try {

            String path = System.getProperty("user.dir");
            if (path == null || path.trim().length() == 0) {
                path = System.getProperty("user.home");
            }
            System.out.println("path: " + path);

//            InputStream iStream = String.class.getResourceAsStream("/resources/logback.xml");
//            if (iStream == null)
//                iStream = HaphazardLogger.class.getResourceAsStream("/resources/logback.xml");
//            if (iStream == null)
//                iStream = this.getClass().getResourceAsStream("/resources/logback.xml");
//            if (iStream == null)
//                iStream = ClassLoader.getSystemClassLoader().getResourceAsStream("/resources/logback.xml");
//            if (iStream == null)
//                iStream = ClassLoader.getSystemResourceAsStream("/resources/logback.xml");
//            if (iStream == null)
//                iStream = HaphazardLogger.class.getClassLoader().getResourceAsStream("/resources/logback.xml");
//            if (iStream == null)
//                iStream = getClass().getClassLoader().getResourceAsStream("/resources/logback.xml");
//            if (iStream == null)
//                iStream = HaphazardLogger.class.getResourceAsStream("resources/logback.xml");
//            if (iStream == null)
//                iStream = this.getClass().getResourceAsStream("resources/logback.xml");
//            if (iStream == null)
//                iStream = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/logback.xml");
//            if (iStream == null)
//                iStream = ClassLoader.getSystemResourceAsStream("resources/logback.xml");
//            if (iStream == null)
//                iStream = HaphazardLogger.class.getClassLoader().getResourceAsStream("resources/logback.xml");
//            if (iStream == null)
//                iStream = getClass().getClassLoader().getResourceAsStream("resources/logback.xml");
//            if (iStream == null)
//                iStream = HaphazardLogger.class.getClassLoader().getResource("app.properties").openStream();
//            if (iStream == null)
//                iStream = HaphazardLogger.class.getResource("app.properties").openStream();
            InputStream iStream = ResolveStream.get("logback.xml");
            JoranConfigurator configurator = new JoranConfigurator();
            //InputStream configStream = FileUtils.openInputStream(logbackPropertiesUserFile);
            configurator.setContext(loggerContext);

            if (iStream != null) {
                configurator.doConfigure(iStream);
            } else {
                configurator.doConfigure(path + "/logback.xml"); // loads logback file
            }            //configStream.close();

        } catch (Exception je) {

            // StatusPrinter will handle this
            je.printStackTrace();
        }

        StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);

        // test...
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
