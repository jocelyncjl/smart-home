import com.security.HelloSecurityGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SecurityClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",8060).usePlaintext().build();
        try{
            HelloSecurityGrpc.HelloSecurityStub securityStub = HelloSecurityGrpc.newStub(managedChannel);




        }catch(Exception e){
            e.printStackTrace();
        }finally{


        }








    }
}
