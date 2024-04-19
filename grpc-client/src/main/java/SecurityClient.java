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
                    if(securityResponse.getWarnings().equals("Detecting an unidentified person entering")){
                        System.out.println(securityResponse.getWarnings());
                        securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOnInstructs("Turn on the camera and alarm").build());
                    }else if(securityResponse.getOperations().equals("Turn on the camera and alarm successfully")){
                        System.out.println(securityResponse.getOperations());
                        securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().setOffInstructs("Turn off the camera and alarm").build());

                    }else if(securityResponse.getDangerRemove().equals("The unknown person has already left")) {
                        System.out.println(securityResponse.getDangerRemove());
                        System.out.println(securityResponse.getOperationsTwo());
                        securityRequestStreamObserver.onCompleted();

                    }



                }
                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    securityRequestStreamObserver.onCompleted();
                }
            });

            securityRequestStreamObserver.onNext(HelloSecurityProto.SecurityRequest.newBuilder().build());
            managedChannel.awaitTermination(12, TimeUnit.SECONDS);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            managedChannel.shutdown();
        }

    }
}
