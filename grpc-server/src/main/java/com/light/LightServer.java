package com.light;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

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
        int port = 8081;
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
        try(FileInputStream fis = new FileInputStream("grpc-server/src/main/resources/consul.properties")){
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
        final LightServer lightServer = new LightServer();
        lightServer.start();
        lightServer.blockUntilShutDown();
    }

    static class LightServiceImpl extends HelloLightGrpc.HelloLightImplBase{
        @Override
        public void lightService(HelloLightProto.LightRequest request, StreamObserver<HelloLightProto.LightResponse> responseObserver) {
            // Accept the request from the client
            String lightcall = request.getLightcall();
            // The message shown on the side of light server
            System.out.println("I have accepted the request command: " + lightcall);
            // Encapsulate the response message used for transmitting to the client
            // Create the constructor
            HelloLightProto.LightResponse.Builder builder = HelloLightProto.LightResponse.newBuilder();
            // Fill the response message
            builder.setLightResult("Increase the light brightness of the table lamp successfully");
            // Encapsulate the response message in a variable lightResponse
            HelloLightProto.LightResponse lightResponse = builder.build();
            // Deliver the response variable to the observer
            responseObserver.onNext(lightResponse);
            // This shows that the observer can know the encapsulation process of response message finishes
            responseObserver.onCompleted();

        }

        @Override
        public void healthCheck(HelloLightProto.HealthCheckRequest request, StreamObserver<HelloLightProto.HealthCheckResponse> responseObserver) {
            HelloLightProto.HealthCheckResponse response = HelloLightProto.HealthCheckResponse.newBuilder().setStatus(200).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

    }

}