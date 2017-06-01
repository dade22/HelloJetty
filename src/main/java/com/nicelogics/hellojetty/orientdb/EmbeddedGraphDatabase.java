/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.orientdb;

import com.nicelogics.hellojetty.utility.Logger;
import com.nicelogics.hellojetty.utility.ResolveStream;
import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.OServerMain;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import java.io.File;
import java.io.InputStream;

/**
 *
 * @author davide
 */
public class EmbeddedGraphDatabase {

    private final static String CLOGNAME = "EmbeddedGraphDatabase";
    
    public void go() {

        try {

            start();

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private void start() throws Exception {

        String orientdbHome = new File("").getAbsolutePath();
        Logger.i(CLOGNAME, "start", "Server orientdbHome: " + orientdbHome);

        String path = System.getProperty("user.dir");
        if (path == null || path.trim().length() == 0) {
            path = System.getProperty("user.home");
        }

        System.setProperty("ORIENTDB_HOME", path + "/target");
        Logger.i(CLOGNAME, "start", "Server starting..");

        InputStream iStream = ResolveStream.get("orientdb.xml");
        OServer server = OServerMain.create();
        server = server.startup(iStream);
        server.activate();
        Logger.i(CLOGNAME, "start", "Server started");

        path += "/target/databases/hellojetty";
        Logger.i(CLOGNAME, "start", "DB Server path: " + path);

        OrientGraphFactory factory = new OrientGraphFactory("plocal:" + path).setupPool(1, 10);
        OrientGraph graph = factory.getTx();

        try {

            Vertex luca = graph.addVertex(null); // 1st OPERATION: IMPLICITLY BEGINS TRANSACTION
            luca.setProperty("name", "Luca");
            Vertex marko = graph.addVertex(null);
            marko.setProperty("name", "Marko");
            Edge lucaKnowsMarko = graph.addEdge(null, luca, marko, "knows");

            graph.commit();

            Logger.i(CLOGNAME, "start", "\r\n\r\ncount edges: " + graph.countEdges());

            Iterable<Edge> edges = graph.getEdges();
            for (Edge edge : edges) {
                Logger.i(CLOGNAME, "start", "edge: " + edge.getLabel() + " id: " + edge.getId()
                        + " OUT: " + edge.getVertex(Direction.OUT).getProperty("name")
                        + " IN: " + edge.getVertex(Direction.IN).getProperty("name"));
                //+ " BOTH: " + edge.getVertex(Direction.BOTH));
            }
            Logger.i(CLOGNAME, "start", "end edges");

        } catch (Exception e) {

            e.printStackTrace();
            graph.rollback();

        } finally {

            // it's necessary..
            graph.shutdown();
        }

        // .........
        
//        ODatabaseDocument db = ... 
//        OElement element = db.newInstance("Person");
//        element.setProperty("name", "John");
//        element.save();

        Logger.i(CLOGNAME, "start", "Server stopping..");
        server.shutdown();
        Logger.i(CLOGNAME, "start", "Server stopped");
    }
}
