package com.light;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import com.light.HelloLightGrpc;
import com.light.HelloLightProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.List;


public class LightClient {
    private ConsulClient consulClient;
    private String consuleServiceName;

    // Constructor method
    public LightClient(String consulHost,int consulPort,String consuleServiceName){
        this.consulClient = new ConsulClient(consulHost,consulPort);
        this.consuleServiceName = consuleServiceName;
    }

    // The method used to make the unary request
    public void makeLightRequest(){
        // Lookup service details from Consul
        List<HealthService> healthServices = consulClient.getHealthServices(consuleServiceName, true, null).getValue();
        if(healthServices.isEmpty()){
            System.err.println("No healthy instances of " + consuleServiceName + " found in Consul.");
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
        HelloLightGrpc.HelloLightBlockingStub lightStub = HelloLightGrpc.newBlockingStub(channel);

        // Prepare and send the unary request
        HelloLightProto.LightRequest lightRequest = HelloLightProto.LightRequest.newBuilder().setLightcall("Please help me increase the light brightness of the table lamp").build();
        HelloLightProto.LightResponse lightResponse = lightStub.lightService(lightRequest);

        // Process the response
        String lightResult = lightResponse.getLightResult();
        System.out.println(lightResult);

        // Shutdown the channel when done
        channel.shutdown();
    }

    public static void main(String[] args) {
        // Consul host
        String consulHost = "localhost";
        // Consul port
        int consulPort = 8500;
        // Name of the service registered in Consul
        String consulServiceName = "light-service";

        LightClient lightClient = new LightClient(consulHost,consulPort,consulServiceName);
        lightClient.makeLightRequest();
    }
}