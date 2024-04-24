package com.appliance;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.temperature.HelloTemperatureProto;
import com.temperature.TemperatureServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class ApplianceServer {
    // Create the grpc server variable to store the server object
    private Server appServer;

    // The method to start the grpc light server
    private void start() throws IOException {
        // The port on which the server should run
        int port = 8000;
        // Create the grpc service instance and add service
        appServer = ServerBuilder.forPort(port).addService(new ApplianceServer.ApplianceServerImpl()).build().start();
        // Output the server launch information
        System.out.println("The Appliance Server started, listening on " + port);
        // Register the appliance server the consul
        registerToConsul();
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("shutting down gRPC server since JVM is shutting down");
            ApplianceServer.this.stop();
            System.err.println("server shut down");
        }));
    }

    // The method to stop grpc server
    private void stop(){
        if(appServer!= null){
            appServer.shutdown();
        }
    }

    // Block and wait the server to close
    private void blockUntilShutDown() throws InterruptedException{
        if(appServer != null){
            appServer.awaitTermination();
        }
    }

    // The method to register the server to consul
    private void registerToConsul(){
        System.out.println("Registering to Consul...");
        // Load configuration files
        Properties props = new Properties();
        try(FileInputStream fis = new FileInputStream("grpc-server/src/main/resources/applianceConsul.properties")){
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

    public static void main(String[] args) throws IOException, InterruptedException {
        final ApplianceServer appServer = new ApplianceServer();
        appServer.start();
        appServer.blockUntilShutDown();
    }

    static class ApplianceServerImpl extends HelloApplianceGrpc.HelloApplianceImplBase{
        @Override
        public StreamObserver<HelloApplianceProto.ApplianceRequest> applianceService(StreamObserver<HelloApplianceProto.ApplianceResponse> responseObserver) {
            return new StreamObserver<HelloApplianceProto.ApplianceRequest>() {
                // Accept request message from the client
                @Override
                public void onNext(HelloApplianceProto.ApplianceRequest applianceRequest) {
                    System.out.println("Accept the command from the client: " + applianceRequest.getApplianceName() + " , " + applianceRequest.getApplianceOperation());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                // Encapsulate the response message
                @Override
                public void onCompleted() {
                    System.out.println("All commands from the client have been accepted");
                    // Provide response message after accepting all request
                    HelloApplianceProto.ApplianceResponse.Builder builder = HelloApplianceProto.ApplianceResponse.newBuilder();
                    builder.setOperationResponse("All operations from the user have been completed successfully");
                    HelloApplianceProto.ApplianceResponse applianceResponse= builder.build();
                    responseObserver.onNext(applianceResponse);
                    responseObserver.onCompleted();
                }
            };
        }

        @Override
        public void healthCheck(HelloApplianceProto.AppHealthCheckRequest request, StreamObserver<HelloApplianceProto.AppHealthCheckResponse> responseObserver) {
            HelloApplianceProto.AppHealthCheckResponse response = HelloApplianceProto.AppHealthCheckResponse.newBuilder().setStatus(220).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
