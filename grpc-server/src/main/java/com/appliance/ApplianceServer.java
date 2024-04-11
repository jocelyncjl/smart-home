package com.appliance;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ApplianceServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Bind the port withe the client
        ServerBuilder serverBuilder = ServerBuilder.forPort(8000);
        // Publish the service
        serverBuilder.addService(new ApplianceServerImpl());
        // Create the server object
        Server applianceServer = serverBuilder.build();
        applianceServer.start();
        applianceServer.awaitTermination();
    }
}
