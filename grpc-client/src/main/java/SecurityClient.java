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
            HelloSecurityGrpc.HelloSecurityStub securityStub = HelloSecurityGrpc.newStub(managedChannel);
            securityRequestStreamObserver = securityStub.securityService(new StreamObserver<HelloSecurityProto.SecurityResponse>() {
                @Override
                public void onNext(HelloSecurityProto.SecurityResponse securityResponse){
                    if (!securityResponse.getWarnings().isEmpty()) {
                        System.out.println(securityResponse.getWarnings());
                        securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOnInstructs("Turn on the camera and alarm").build());
                    }
                    if (!securityResponse.getOperations().isEmpty()) {
                        System.out.println(securityResponse.getOperations());
                    }
                    if (!securityResponse.getDangerRemove().isEmpty()) {
                        System.out.println(securityResponse.getDangerRemove());
                        securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOffInstructs("Turn off the camera and alarm").build());
                    }
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
