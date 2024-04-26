package com.appliance;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplianceClient {
    private ConsulClient consulClient;
    private String consulServiceName;

    // Constructor method
    public ApplianceClient(String consulHost,int consulPort,String consulServiceName){
        this.consulClient = new ConsulClient(consulHost,consulPort);
        this.consulServiceName = consulServiceName;
    }

    // The method used to make the server-side Streaming request
    public void makeAppRequest() throws InterruptedException {
        // Lookup service details from Consul
        List<HealthService> healthServices = consulClient.getHealthServices(consulServiceName, true, null).getValue();
        if(healthServices.isEmpty()){
            System.err.println("No healthy instances of " + consulServiceName + " found in Consul.");
            return;
        }

        // Pick the first healthy instance (you can implement a load balancing strategy here)
        HealthService healthService = healthServices.get(0);

        // Debug output for service details
        System.out.println("Service details from Consul:");
        System.out.println("Service ID: " + healthService.getService().getId());
        System.out.println("Service Name: " + healthService.getService().getService());
        System.out.println("Service Address: " + healthService.getService().getAddress());
        System.out.println("Service Port: " + healthService.getService().getPort());

        // Extract host and port from the service details
        String serverHost = healthService.getService().getAddress();
        int serverPort = healthService.getService().getPort();

        // Debug output for extracted host and port
        System.out.println("Server host: " + serverHost);
        System.out.println("Server port: " + serverPort);

        // Create a gRPC channel to connect to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverHost,serverPort).usePlaintext().build();

        // Create a stub for the gRPC service
        HelloApplianceGrpc.HelloApplianceStub applianceStub = HelloApplianceGrpc.newStub(channel);

        // Process the response
        StreamObserver<HelloApplianceProto.ApplianceRequest> applianceRequestObserver = applianceStub.applianceService(new StreamObserver<HelloApplianceProto.ApplianceResponse>() {
            @Override
            public void onNext(HelloApplianceProto.ApplianceResponse applianceResponse) {
                // Obtain the message from the server
                System.out.println(applianceResponse.getOperationResponse());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("The appliance server responses end");
            }
        });

        // Prepare and send the request
        for(int i = 0;i < 3;i++){
            if(i == 0){
                HelloApplianceProto.ApplianceRequest.Builder builder = HelloApplianceProto.ApplianceRequest.newBuilder();
                builder.setApplianceName("television");
                builder.setApplianceOperation("turn on");
                i++;
                HelloApplianceProto.ApplianceRequest applianceRequest = builder.build();
                applianceRequestObserver.onNext(applianceRequest);
            }
            if(i == 2){
                HelloApplianceProto.ApplianceRequest.Builder builder = HelloApplianceProto.ApplianceRequest.newBuilder();
                builder.setApplianceName("washing machine");
                builder.setApplianceOperation("turn off");
                i++;
                HelloApplianceProto.ApplianceRequest applianceRequest = builder.build();
                applianceRequestObserver.onNext(applianceRequest);
            }
            if(i == 3){
                HelloApplianceProto.ApplianceRequest.Builder builder = HelloApplianceProto.ApplianceRequest.newBuilder();
                builder.setApplianceName("music player");
                builder.setApplianceOperation("increase the volume to 80");
                i++;
                HelloApplianceProto.ApplianceRequest applianceRequest = builder.build();
                applianceRequestObserver.onNext(applianceRequest);

            }
        }
        applianceRequestObserver.onCompleted();
        channel.awaitTermination(20, TimeUnit.SECONDS);

        // Shutdown the channel when done
        channel.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        // Consul host
        String consulHost = "localhost";
        // Consul port
        int consulPort = 8500;
        // Name of the service registered in Consul
        String consulServiceName = "appliance-service";

        ApplianceClient appClient = new ApplianceClient(consulHost,consulPort,consulServiceName);
        appClient.makeAppRequest();

    }
}
