package com.appliance;

import com.temperature.HelloTemperatureGrpc;
import com.temperature.HelloTemperatureProto;
import io.grpc.stub.StreamObserver;

public class ApplianceServerImpl extends HelloApplianceGrpc.HelloApplianceImplBase {
    // Overwrite the grpc appliance service method
    @Override
    public StreamObserver<HelloApplianceProto.ApplianceRequest> applianceService(StreamObserver<HelloApplianceProto.ApplianceResponse> responseObserver) {
       return new StreamObserver<HelloApplianceProto.ApplianceRequest>() {
           // Accept request message from the client
           @Override
           public void onNext(HelloApplianceProto.ApplianceRequest applianceRequest) {
               System.out.println("Accept the command from the client: " + applianceRequest.getApplianceName() + " , " + applianceRequest.getApplianceOperation());
           }

           @Override
           public void onError(Throwable throwable) {

           }

           // Encapsulate the response message
           @Override
           public void onCompleted() {
               System.out.println("All commands from the client have been accepted");
               // Provide response message after accepting all request
               HelloApplianceProto.ApplianceResponse.Builder builder = HelloApplianceProto.ApplianceResponse.newBuilder();
               builder.setOperationResponse("All operations from the user have been completed successfully");
               HelloApplianceProto.ApplianceResponse applianceResponse= builder.build();
               responseObserver.onNext(applianceResponse);
               responseObserver.onCompleted();
           }
       };
    }
}
