package com.light;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class LightServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Bind the port
        ServerBuilder serverBuilder = ServerBuilder.forPort(8080);
        // Publish the service of table lamp light brightness control
        serverBuilder.addService(new LightServiceImpl());
        // Create the server object
        Server server = serverBuilder.build();
        server.start();
        server.awaitTermination();
    }
}
