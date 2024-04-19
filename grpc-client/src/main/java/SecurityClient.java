import com.security.HelloSecurityGrpc;
import com.security.HelloSecurityProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class SecurityClient {
    private static StreamObserver<HelloSecurityProto.SecurityRequest> securityRequestStreamObserver;

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",8060).usePlaintext().build();
        try{
            // Create the agency to invoke the grpc method
            HelloSecurityGrpc.HelloSecurityStub securityStub = HelloSecurityGrpc.newStub(managedChannel);
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
            managedChannel.awaitTermination(12, TimeUnit.SECONDS);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            managedChannel.shutdown();
        }

    }
}
