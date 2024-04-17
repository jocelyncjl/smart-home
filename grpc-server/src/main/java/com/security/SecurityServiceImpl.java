package com.security;

import io.grpc.stub.StreamObserver;

public class SecurityServiceImpl extends HelloSecurityGrpc.HelloSecurityImplBase{
    @Override
    public StreamObserver<HelloSecurityProto.SecurityRequest> securityService(StreamObserver<HelloSecurityProto.SecurityResponse> responseObserver) {
        return new StreamObserver<HelloSecurityProto.SecurityRequest>() {
            @Override
            public void onNext(HelloSecurityProto.SecurityRequest securityRequest) {
                responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setWarnings("Detecting an unidentified person entering").build());
                System.out.println("Accepted the instruction: " + securityRequest.getOnInstructs());
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
