package com.security;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.temperature.TemperatureServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class SecurityServer {
    // Create the grpc server variable to store the server object
    private Server securityServer;

    // The method to start the grpc security server
    private void start() throws IOException {
        // The port on which the server should run
        int port = 8060;
        // Create the grpc service instance and add service
        securityServer = ServerBuilder.forPort(port).addService(new SecurityServer.SecurityServiceImpl()).build().start();
        // Output the server launch information
        System.out.println("The Temperature Server started, listening on " + port);
        // Register the temperature server the consul
        registerToConsul();
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("shutting down gRPC server since JVM is shutting down");
            SecurityServer.this.stop();
            System.err.println("server shut down");
        }));
    }

    // The method to stop grpc server
    private void stop(){
        if(securityServer!= null){
            securityServer.shutdown();
        }
    }

    // Block and wait the server to close
    private void blockUntilShutDown() throws InterruptedException{
        if(securityServer != null){
            securityServer.awaitTermination();
        }
    }

    // The method to register the server to consul
    private void registerToConsul(){
        System.out.println("Registering to Consul...");
        // Load configuration files
        Properties props = new Properties();
        try(FileInputStream fis = new FileInputStream("grpc-server/src/main/resources/securityConsul.properties")){
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
        final SecurityServer securityServer = new SecurityServer();
        securityServer.start();
        securityServer.blockUntilShutDown();
    }

    static class SecurityServiceImpl extends HelloSecurityGrpc.HelloSecurityImplBase{
        public StreamObserver<HelloSecurityProto.SecurityRequest> securityService(StreamObserver<HelloSecurityProto.SecurityResponse> responseObserver) {
            // The server sends the security warning to the client as it detects some danger
            responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setWarnings("Detecting an unidentified person entering").build());
            return new StreamObserver<HelloSecurityProto.SecurityRequest>() {
                @Override
                public void onNext(HelloSecurityProto.SecurityRequest securityRequest) {
                    // The server write commands sent to the client as it accepts info "Turn on the camera and alarm" from the client
                    if (securityRequest.getOnInstructs().equals("Turn on the camera and alarm")) {
                        System.out.println(securityRequest.getOnInstructs());
                        responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setOperations("Turn on the camera and alarm successfully").build());
                        responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setDangerRemove("The unknown person has already left").build());
                    }
                    // The server write operation info "Turn off the camera and alarm successfully" back to the client as it accepts "Turn off" command
                    if (securityRequest.getOffInstructs().equals("Turn off the camera and alarm")) {
                        System.out.println(securityRequest.getOffInstructs());
                        responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setOperationsTwo("Turn off the camera and alarm successfully").build());
                        responseObserver.onCompleted();
                    }

                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };

        }

        @Override
        public void healthCheck(HelloSecurityProto.SecurityHealthCheckRequest request, StreamObserver<HelloSecurityProto.SecurityHealthCheckResponse> responseObserver) {
            super.healthCheck(request, responseObserver);
        }
    }
}
