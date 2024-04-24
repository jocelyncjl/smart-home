import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import com.temperature.HelloTemperatureGrpc;
import com.temperature.HelloTemperatureProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;
import java.util.List;

public class TemperatureClient {
    private ConsulClient consulClient;
    private String consuleServiceName;

    // Constructor method
    public TemperatureClient(String consulHost,int consulPort,String consuleServiceName){
        this.consulClient = new ConsulClient(consulHost,consulPort);
        this.consuleServiceName = consuleServiceName;
    }

    // The method used to make the server-side Streaming request
    public void makeTempRequest(){
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
        HelloTemperatureGrpc.HelloTemperatureBlockingStub tempService = HelloTemperatureGrpc.newBlockingStub(channel);


        // Prepare and send the unary request
        HelloTemperatureProto.TemperatureRequest.Builder builder = HelloTemperatureProto.TemperatureRequest.newBuilder();
        builder.setTempCall("Please help me monitor the temperature of different home areas at the current time");
        HelloTemperatureProto.TemperatureRequest tempRequest = builder.build();
        Iterator<HelloTemperatureProto.TemperatureResponse> tempResponseIterator = tempService.temperatureService(tempRequest);

        // Process the response
        while(tempResponseIterator.hasNext()){
            HelloTemperatureProto.TemperatureResponse tempResponse = tempResponseIterator.next();
            String homeArea = tempResponse.getHomeArea();
            double degreeCelsius = tempResponse.getDegreeCelsius();
            System.out.println("The temperature in the " + homeArea + " is " + degreeCelsius + "°C");
        }

        // Shutdown the channel when done
        channel.shutdown();
    }

    public static void main(String[] args) {
        // Consul host
        String consulHost = "localhost";
        // Consul port
        int consulPort = 8500;
        // Name of the service registered in Consul
        String consulServiceName = "temperature-service";

        TemperatureClient tempClient = new TemperatureClient(consulHost,consulPort,consulServiceName);
        tempClient.makeTempRequest();
    }
}
