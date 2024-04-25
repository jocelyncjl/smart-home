import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import com.security.HelloSecurityGrpc;
import com.security.HelloSecurityProto;
import com.temperature.HelloTemperatureGrpc;
import com.temperature.HelloTemperatureProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SecurityClient {
    private ConsulClient consulClient;
    private String consuleServiceName;


    private static StreamObserver<HelloSecurityProto.SecurityRequest> securityRequestStreamObserver;
    // Constructor method
    public SecurityClient(String consulHost,int consulPort,String consuleServiceName){
        this.consulClient = new ConsulClient(consulHost,consulPort);
        this.consuleServiceName = consuleServiceName;
    }

    // The method used to make the bidirectional Streaming request
    public void makeSecurityRequest() throws InterruptedException {
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
        HelloSecurityGrpc.HelloSecurityStub securityStub = HelloSecurityGrpc.newStub(channel);

        // The agency stub invokes the grpc method

        securityRequestStreamObserver = securityStub.securityService(new StreamObserver<HelloSecurityProto.SecurityResponse>() {
            @Override
            public void onNext(HelloSecurityProto.SecurityResponse securityResponse){
                // The client sends the command as it accepts danger warning info from the server
                if (!securityResponse.getWarnings().isEmpty()) {
                    System.out.println(securityResponse.getWarnings());
                    securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOnInstructs("Turn on the camera and alarm").build());
                }
                // The client accepts data from the server as it completes operation successfully
                if (!securityResponse.getOperations().isEmpty()) {
                    System.out.println(securityResponse.getOperations());
                }
                // The client accepts danger lift tips from the server and sends another command to the server
                if (!securityResponse.getDangerRemove().isEmpty()) {
                    System.out.println(securityResponse.getDangerRemove());
                    securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOffInstructs("Turn off the camera and alarm").build());
                }
                // The client accepts the successful operations from the server and client requests end
                if (!securityResponse.getOperationsTwo().isEmpty()) {
                    System.out.println(securityResponse.getOperationsTwo());
                    securityRequestStreamObserver.onCompleted();
                }
            }
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });
        channel.awaitTermination(12, TimeUnit.SECONDS);

        // Shutdown the channel when done
        channel.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        // Consul host
        String consulHost = "localhost";
        // Consul port
        int consulPort = 8500;
        // Name of the service registered in Consul
        String consulServiceName = "security-service";

        SecurityClient tempClient = new SecurityClient(consulHost,consulPort,consulServiceName);
        tempClient.makeSecurityRequest();
    }
}
