/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.orientdb;

import com.nicelogics.hellojetty.structures.Account;
import com.nicelogics.hellojetty.utility.Logger;
import com.orientechnologies.orient.core.db.ODatabaseType;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.OrientDBConfigBuilder;
import com.orientechnologies.orient.core.db.object.ODatabaseObject;
import com.orientechnologies.orient.object.db.OrientDBObject;
import java.io.File;

/**
 *
 * @author davide
 */
public class EmbeddedObjectDatabase {

    private final static String CLOGNAME = "EmbeddedObjectDatabase";
    
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
        Logger.i(CLOGNAME, "start", "Load config");
        
        OrientDBConfigBuilder builder = OrientDBConfig.builder();
        OrientDBConfig config = builder.build();

        path += "/target/databases";
        Logger.i(CLOGNAME, "start", "Server path: " + path);

        Logger.i(CLOGNAME, "start", "Server starting..");
        OrientDBObject orientDB = new OrientDBObject("plocal:"+path, config);
        if (!orientDB.exists("test")) {

            orientDB.create("test", ODatabaseType.PLOCAL, config);
        }

        //ODatabaseObject db = orientDB.open("test", "root", "root");
        ODatabaseObject db = orientDB.open("test", "test", null);
        db.createClass("Account");
        db.close();

        Logger.i(CLOGNAME, "start", "Server started");

        db.getEntityManager().registerEntityClass(Account.class);

        // CREATE NEW PROXIED OBJECT AND FILL IT
        Account account = db.newInstance(Account.class);
        account.setName("Tully");
        account.setSurname("Cicero");

        // SAVE
        db.save(account);

        // CREATE NEW PROXIED OBJECT AND FILL IT
        account = new Account();
        account.setName("Alessandro");
        account.setSurname("Manzoni");

        // SAVE ACCOUNT: ORIENTDB SERIALIZES OBJECT & GIVES PROXIED INSTANCE
        account = db.save(account);
        
        Logger.i(CLOGNAME, "start", "Accounts count: " + db.countClass("Account"));
        
        for (Account acc : db.browseClass(Account.class)) {

            Logger.i(CLOGNAME, "start", "found Account " + acc.getName() + " " + acc.getSurname() );
        }

        Logger.i(CLOGNAME, "start", "Server stopping..");
        db.close();
        Logger.i(CLOGNAME, "start", "Server stopped");
    }
}
