import com.temperature.HelloTemperatureGrpc;
import com.temperature.HelloTemperatureProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class TemperatureClient {
    public static void main(String[] args) {
        // Create the communication channel between Client and Server
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",8090).usePlaintext().build();
        try{
            // Create the agency to invoke the service method
            HelloTemperatureGrpc.HelloTemperatureBlockingStub tempService = HelloTemperatureGrpc.newBlockingStub(managedChannel);
            // Encapsulate request message on the side of the client
            HelloTemperatureProto.TemperatureRequest.Builder builder = HelloTemperatureProto.TemperatureRequest.newBuilder();
            builder.setTempCall("Please help me monitor the temperature of different home areas at the current time");
            HelloTemperatureProto.TemperatureRequest tempRequest = builder.build();
            // The Stub invokes the grpc method to obtain the response messages
            Iterator<HelloTemperatureProto.TemperatureResponse> tempResponseIterator = tempService.temperatureService(tempRequest);
            while(tempResponseIterator.hasNext()){
                HelloTemperatureProto.TemperatureResponse tempResponse = tempResponseIterator.next();
                String homeArea = tempResponse.getHomeArea();
                double degreeCelsius = tempResponse.getDegreeCelsius();
                System.out.println("The temperature in the " + homeArea + " is " + degreeCelsius + "°C");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            managedChannel.shutdown();
        }
    }
}
