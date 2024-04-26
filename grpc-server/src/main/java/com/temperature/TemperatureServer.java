package com.temperature;

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

public class TemperatureServer{
    // Create the grpc server variable to store the server object
    private Server tempServer;

    // The method to start the grpc light server
    private void start() throws IOException {
        // The port on which the server should run
        int port = 8090;
        // Create the grpc service instance and add service
        tempServer = ServerBuilder.forPort(port).addService(new TemperatureServer.TemperatureServerImpl()).build().start();
        // Output the server launch information
        System.out.println("The Temperature Server started, listening on " + port);
        // Register the temperature server the consul
        registerToConsul();
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("shutting down gRPC server since JVM is shutting down");
            TemperatureServer.this.stop();
            System.err.println("server shut down");
        }));
    }

    // The method to stop grpc server
    private void stop(){
        if(tempServer!= null){
            tempServer.shutdown();
        }
    }

    // Block and wait the server to close
    private void blockUntilShutDown() throws InterruptedException{
        if(tempServer != null){
            tempServer.awaitTermination();
        }
    }

    // The method to register the server to consul
    private void registerToConsul(){
        System.out.println("Registering to Consul...");
        // Load configuration files
        Properties props = new Properties();
        try(FileInputStream fis = new FileInputStream("grpc-server/src/main/resources/tempConsul.properties")){
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
        final TemperatureServer tempServer = new TemperatureServer();
        tempServer.start();
        tempServer.blockUntilShutDown();
    }

    static class TemperatureServerImpl extends HelloTemperatureGrpc.HelloTemperatureImplBase{
        @Override
        public void temperatureService(HelloTemperatureProto.TemperatureRequest request, StreamObserver<HelloTemperatureProto.TemperatureResponse> responseObserver) {
            // Acquire and display the require message of the client
            String tempCall = request.getTempCall();
            System.out.println(tempCall);
            // Encapsulate the response messages to transmit to the client
            for(int i = 0;i < 3;i++){
                if(i == 0){
                    // Create the builder and fill message with home area and temperature
                    HelloTemperatureProto.TemperatureResponse.Builder builder = HelloTemperatureProto.TemperatureResponse.newBuilder();
                    builder.setHomeArea("living room");
                    builder.setDegreeCelsius(23);
                    i++;
                    HelloTemperatureProto.TemperatureResponse tempResponse = builder.build();
                    // Deliver the response variable to the observer
                    responseObserver.onNext(tempResponse);
                    // Give some sleep time to echo to the client
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(i == 1){
                    // Create the builder and fill message with home area and temperature
                    HelloTemperatureProto.TemperatureResponse.Builder builderTwo = HelloTemperatureProto.TemperatureResponse.newBuilder();
                    builderTwo.setHomeArea("kitchen");
                    builderTwo.setDegreeCelsius(35);
                    i++;
                    HelloTemperatureProto.TemperatureResponse tempResponseTwo = builderTwo.build();
                    // Deliver the response variable to the observer
                    responseObserver.onNext(tempResponseTwo);
                    // Give some sleep time to echo to the client
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(i == 2){
                    // Create the builder and fill message with home area and temperature
                    HelloTemperatureProto.TemperatureResponse.Builder builderThree = HelloTemperatureProto.TemperatureResponse.newBuilder();
                    builderThree.setHomeArea("bedroom");
                    builderThree.setDegreeCelsius(28);
                    i++;
                    HelloTemperatureProto.TemperatureResponse tempResponseThree = builderThree.build();
                    // Deliver the response variable to the observer
                    responseObserver.onNext(tempResponseThree);
                    // Give some sleep time to echo to the client
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            // The observer can see that the encapsulate process finishes
            responseObserver.onCompleted();
        }

        @Override
        public void healthCheck(HelloTemperatureProto.TempHealthCheckRequest request, StreamObserver<HelloTemperatureProto.TempHealthCheckResponse> responseObserver) {
            HelloTemperatureProto.TempHealthCheckResponse response = HelloTemperatureProto.TempHealthCheckResponse.newBuilder().setStatus(210).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}




