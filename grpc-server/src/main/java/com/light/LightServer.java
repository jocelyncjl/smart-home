package com.light;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class LightServer {
    // Create the grpc server variable to store the server object
    private Server server;
    // The method to start the grpc light server
    private void start() throws IOException {
        // The port on which the server should run
        int port = 8080;
        // Create the grpc service instance and add service
        server = ServerBuilder.forPort(port).addService(new LightServiceImpl()).build().start();
        // Output the server launch information
        System.out.println("The Light Server started, listening on " + port);
        // Register the light server the consul
        registerToConsul();
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("shutting down gRPC server since JVM is shutting down");
            LightServer.this.stop();
            System.err.println("server shut down");
        }));
    }
    // The method to stop grpc server
    private void stop(){
        if(server!= null){
            server.shutdown();
        }
    }
    // Block and wait the server to close
    private void blockUntilShutDown() throws InterruptedException{
        if(server != null){
            server.awaitTermination();
        }
    }
    // The method to register the server to consul
    private void registerToConsul(){
        System.out.println("Registering to Consul...");
        // Load configuration files
        Properties props = new Properties();
        try(FileInputStream fis = new FileInputStream("src/main/resources/consul.properties")){
            props.load(fis);
        }catch(IOException e){
            e.printStackTrace();
            return;
        }
        // Extract the consul configuration attributes
        // Consul host
        String consulHost = props.getProperty("consul.host");
        // Consul port
        int consulPort = Integer.parseInt(props.getProperty("consul.port"));
        // Service name
        String serviceName = props.getProperty("consul.service.name");
        // Service port
        int servicePort = Integer.parseInt(props.getProperty("consul.service.port"));
        // Health check interval
        String healthCheckInterval = props.getProperty("consul.service.healthCheckInterval");

        // Gain host address
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // Create consul client
        ConsulClient consulClient = new ConsulClient(consulHost,consulPort);
        // Define the details of new service
        NewService newService = new NewService();
        newService.setName(serviceName);
        newService.setPort(servicePort);
        newService.setAddress(hostAddress);
        // register service in consul
        consulClient.agentServiceRegister(newService);
        // Print out register information
        System.out.println("Server registered to Consul successfully. Host: " + hostAddress);
    }

    // The main method to start the server
    public static void main(String[] args) throws IOException, InterruptedException {
        final LightServer ligtServer = new LightServer();
        ligtServer.start();
        ligtServer.blockUntilShutDown();

    }
}
