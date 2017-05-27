/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.utility;

import com.nicelogics.hellojetty.logback.HaphazardLogger;
import java.io.InputStream;

/**
 *
 * @author davide
 */
public class ResolveStream {

    public static InputStream get(String resourceName) throws Exception {

        InputStream iStream = String.class.getResourceAsStream(resourceName);
        if (iStream == null) {
            iStream = ResolveStream.class.getResourceAsStream(resourceName);
        }
        if (iStream == null) {
            iStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName);
        }
        if (iStream == null) {
            iStream = ClassLoader.getSystemResourceAsStream(resourceName);
        }
        if (iStream == null) {
            iStream = ResolveStream.class.getClassLoader().getResourceAsStream(resourceName);
        }
        if (iStream == null) {
            iStream = ResolveStream.class.getResource(resourceName.replaceFirst("/", "")).openStream();
        }
        return iStream;
    }
}
