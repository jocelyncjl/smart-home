package com.light;

import io.grpc.stub.StreamObserver;

public class LightServiceImpl extends HelloLightGrpc.HelloLightImplBase{
    @Override
    public void lightService(HelloLightProto.LightRequest request, StreamObserver<HelloLightProto.LightResponse> responseObserver) {
        // Accept the request from the client
        String lightcall = request.getLightcall();
        // The message shown on the side of light server
        System.out.println("I have accepted the request command: " + lightcall);
        // Encapsulate the response message used for transmitting to the client
        // Create the constructor
        HelloLightProto.LightResponse.Builder builder = HelloLightProto.LightResponse.newBuilder();
        // Fill the response message
        builder.setLightResult("Increase the light brightness of the table lamp successfully");
        // Encapsulate the response message in a variable lightResponse
        HelloLightProto.LightResponse lightResponse = builder.build();
        // Deliver the response variable to the observer
        responseObserver.onNext(lightResponse);
        // This shows that the observer can know the encapsulation process of response message finishes
        responseObserver.onCompleted();

    }

    @Override
    public void healthCheck(HelloLightProto.HealthCheckRequest request, StreamObserver<HelloLightProto.HealthCheckResponse> responseObserver) {
        HelloLightProto.HealthCheckResponse response = HelloLightProto.HealthCheckResponse.newBuilder().setStatus(200).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
