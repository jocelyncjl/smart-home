import com.appliance.HelloApplianceGrpc;
import com.appliance.HelloApplianceProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class ApplianceClient {
    public static void main(String[] args) {
        // Create the comminication channel
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",8000).usePlaintext().build();
        //
        try{
            // Create the stub agency to invoke the service method
            HelloApplianceGrpc.HelloApplianceStub applianceStub = HelloApplianceGrpc.newStub(managedChannel);
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
            managedChannel.awaitTermination(20, TimeUnit.SECONDS);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            managedChannel.shutdown();
        }
    }
}
