/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsweb.call;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author PC
 */
public class App {

    public static void main(String[] args) {

        Properties prop = new Properties();
        OutputStream output = null;
        /**
         *
         * ip = 10.64.100.80 database = userdb collection = user port = 27017
         */
        try {
            output = new FileOutputStream("db.properties");
            // set the properties value 
            prop.setProperty("ip", "10.64.100.80");
            prop.setProperty("database", "userdb");
            prop.setProperty("collection", "user");
            prop.setProperty("port", "27017");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
