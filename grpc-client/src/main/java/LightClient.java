import com.light.HelloLightGrpc;
import com.light.HelloLightProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class LightClient {
    public static void main(String[] args) {
        // Create the communication channel between Client and Server
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        try {
            // Obtain the agency stub
            HelloLightGrpc.HelloLightBlockingStub lightStub = HelloLightGrpc.newBlockingStub(managedChannel);
            // Prepare and encapsulate the call message
            HelloLightProto.LightRequest.Builder builder = HelloLightProto.LightRequest.newBuilder();
            builder.setLightcall("Please help me increase the light brightness of the table lamp");
            HelloLightProto.LightRequest lightRequest = builder.build();
            // invoke the lightService method to implement communication
            HelloLightProto.LightResponse lightResponse = lightStub.lightService(lightRequest);
            String lightResult = lightResponse.getLightResult();
            System.out.println(lightResult);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            managedChannel.shutdown();
        }
    }
}
