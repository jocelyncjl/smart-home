package com.temperature;

import io.grpc.stub.StreamObserver;

public class TemperatureServerImpl extends HelloTemperatureGrpc.HelloTemperatureImplBase{
    @Override
    public void temperatureService(HelloTemperatureProto.TemperatureRequest request, StreamObserver<HelloTemperatureProto.TemperatureResponse> responseObserver) {
        // Acquire and display the require message of the client
        String tempCall = request.getTempCall();
        System.out.println(tempCall);
        // Encapsulate the response messages to transmit to the client
        for(int i = 0;i < 3;i++){
            if(i == 0){
                // Create the builder and fill message with home area and temperature
                HelloTemperatureProto.TemperatureResponse.Builder builder = HelloTemperatureProto.TemperatureResponse.newBuilder();
                builder.setHomeArea("living room");
                builder.setDegreeCelsius(23);
                i++;
                HelloTemperatureProto.TemperatureResponse tempResponse = builder.build();
                // Deliver the response variable to the observer
                responseObserver.onNext(tempResponse);
                // Give some sleep time to echo to the client
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(i == 1){
                // Create the builder and fill message with home area and temperature
                HelloTemperatureProto.TemperatureResponse.Builder builderTwo = HelloTemperatureProto.TemperatureResponse.newBuilder();
                builderTwo.setHomeArea("kitch");
                builderTwo.setDegreeCelsius(35);
                i++;
                HelloTemperatureProto.TemperatureResponse tempResponseTwo = builderTwo.build();
                // Deliver the response variable to the observer
                responseObserver.onNext(tempResponseTwo);
                // Give some sleep time to echo to the client
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(i == 2){
                // Create the builder and fill message with home area and temperature
                HelloTemperatureProto.TemperatureResponse.Builder builderThree = HelloTemperatureProto.TemperatureResponse.newBuilder();
                builderThree.setHomeArea("bedroom");
                builderThree.setDegreeCelsius(28);
                i++;
                HelloTemperatureProto.TemperatureResponse tempResponseThree = builderThree.build();
                // Deliver the response variable to the observer
                responseObserver.onNext(tempResponseThree);
                // Give some sleep time to echo to the client
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // The observer can see that the encapsulate process finishes
        responseObserver.onCompleted();
    }
}
