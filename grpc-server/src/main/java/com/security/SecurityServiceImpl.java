package com.security;

import io.grpc.stub.StreamObserver;

public class SecurityServiceImpl extends HelloSecurityGrpc.HelloSecurityImplBase{
    @Override
    public StreamObserver<HelloSecurityProto.SecurityRequest> securityService(StreamObserver<HelloSecurityProto.SecurityResponse> responseObserver) {
        return new StreamObserver<HelloSecurityProto.SecurityRequest>() {
            @Override
            public void onNext(HelloSecurityProto.SecurityRequest securityRequest) {
                responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setWarnings("Detecting an unidentified person entering").build());
                if(securityRequest.getOnInstructs().equals("Turn on the camera and alarm")){
                    System.out.println(securityRequest.getOnInstructs());
                    responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setOperations("Turn on the camera and alarm successfully").build());
                }
                if(securityRequest.getOffInstructs().equals("Turn off the camera and alarm")){
                    System.out.println(securityRequest.getOffInstructs());
                    responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setDangerRemove("The unknown person has already left").build());
                    responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setOperationsTwo("Turn off the camera and alarm successfully").build());
                    responseObserver.onCompleted();
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}