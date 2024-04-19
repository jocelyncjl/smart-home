package com.security;

import io.grpc.stub.StreamObserver;

public class SecurityServiceImpl extends HelloSecurityGrpc.HelloSecurityImplBase{
    @Override
    public StreamObserver<HelloSecurityProto.SecurityRequest> securityService(StreamObserver<HelloSecurityProto.SecurityResponse> responseObserver) {
        // The server sends the security warning to the client as it detects some danger
        responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setWarnings("Detecting an unidentified person entering").build());
        return new StreamObserver<HelloSecurityProto.SecurityRequest>() {
            @Override
            public void onNext(HelloSecurityProto.SecurityRequest securityRequest) {
                // The server write commands sent to the client as it accepts info "Turn on the camera and alarm" from the client
                if (securityRequest.getOnInstructs().equals("Turn on the camera and alarm")) {
                    System.out.println(securityRequest.getOnInstructs());
                    responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setOperations("Turn on the camera and alarm successfully").build());
                    responseObserver.onNext(HelloSecurityProto.SecurityResponse.newBuilder().setDangerRemove("The unknown person has already left").build());
                }
                // The server write operation info "Turn off the camera and alarm successfully" back to the client as it accepts "Turn off" command
                if (securityRequest.getOffInstructs().equals("Turn off the camera and alarm")) {
                    System.out.println(securityRequest.getOffInstructs());
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
