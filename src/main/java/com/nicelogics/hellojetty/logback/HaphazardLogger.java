/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.nicelogics.hellojetty.resources.Index;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author davide
 */
public class HaphazardLogger {

    private static final Logger logger = LoggerFactory.getLogger(Index.class);

    public void demo() {

        // applica logback.xml
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();
        try {
            
            String path = System.getProperty("user.dir");
            if (path == null || path.trim().length() == 0)
                path = System.getProperty("user.home");
            
            InputStream iStream = getClass().getResourceAsStream("/resources/logback.xml");
            
            JoranConfigurator configurator = new JoranConfigurator();
            //InputStream configStream = FileUtils.openInputStream(logbackPropertiesUserFile);
            configurator.setContext(loggerContext);

            if (iStream != null) configurator.doConfigure(iStream);
            else configurator.doConfigure(path+"/logback.xml"); // loads logback file
            //configStream.close();

        } catch (JoranException je) {

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
