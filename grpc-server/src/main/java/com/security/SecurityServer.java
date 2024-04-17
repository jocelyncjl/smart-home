package com.security;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class SecurityServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Bind the port
        ServerBuilder serverBuilder = ServerBuilder.forPort(8060);
        // Publish the service of detecting the danger in the house
        serverBuilder.addService(new SecurityServiceImpl());
        // Create the server object
        Server server = serverBuilder.build();
        server.start();
        server.awaitTermination();
    }
}
