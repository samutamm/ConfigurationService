
package com.mycompany.configurationservice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.lang.management.ManagementFactory;
import spark.Spark;
import static spark.Spark.get;


public class ConfApi {

    private String confFilePath = System.getProperty("user.dir") + "/configuration.json";
    
    public ConfApi() {
        configureServer();
        createRoutes();
    }

    private void configureServer() {
        String port = System.getenv("PORT");
        if (port != null) {
            Spark.port(Integer.parseInt(port));
        }
    }

    private void createRoutes() {
        get("/ping", (request, response) -> {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            String dir = System.getProperty("user.dir");

            return "{ \"name\": \"" + name + "\", \"dir\": \"" + dir + "\" }";
        });
        
        get("/configurations", (request, response) -> {
            JsonParser parser = new JsonParser();
            return parser.parse(new FileReader(confFilePath)).getAsJsonObject();
        });
    }
}
