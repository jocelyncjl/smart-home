package com.temperature;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class TemperatureServer{
    public static void main(String[] args) throws IOException, InterruptedException {
        // Create the server builder and connect with the port
        ServerBuilder serverBuilder = ServerBuilder.forPort(8090);
        // Publish the temperature monitoring service
        serverBuilder.addService(new TemperatureServerImpl());
        // Create the server object
        Server tempServer = serverBuilder.build();
        tempServer.start();
        tempServer.awaitTermination();
    }
}
