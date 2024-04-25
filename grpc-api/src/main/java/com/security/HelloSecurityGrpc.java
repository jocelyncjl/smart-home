package com.security;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: Security.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HelloSecurityGrpc {

  private HelloSecurityGrpc() {}

  public static final String SERVICE_NAME = "HelloSecurity";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<HelloSecurityProto.SecurityRequest,
      HelloSecurityProto.SecurityResponse> getSecurityServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "securityService",
      requestType = HelloSecurityProto.SecurityRequest.class,
      responseType = HelloSecurityProto.SecurityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<HelloSecurityProto.SecurityRequest,
      HelloSecurityProto.SecurityResponse> getSecurityServiceMethod() {
    io.grpc.MethodDescriptor<HelloSecurityProto.SecurityRequest, HelloSecurityProto.SecurityResponse> getSecurityServiceMethod;
    if ((getSecurityServiceMethod = HelloSecurityGrpc.getSecurityServiceMethod) == null) {
      synchronized (HelloSecurityGrpc.class) {
        if ((getSecurityServiceMethod = HelloSecurityGrpc.getSecurityServiceMethod) == null) {
          HelloSecurityGrpc.getSecurityServiceMethod = getSecurityServiceMethod =
              io.grpc.MethodDescriptor.<HelloSecurityProto.SecurityRequest, HelloSecurityProto.SecurityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "securityService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloSecurityProto.SecurityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloSecurityProto.SecurityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloSecurityMethodDescriptorSupplier("securityService"))
              .build();
        }
      }
    }
    return getSecurityServiceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<HelloSecurityProto.SecurityHealthCheckRequest,
      HelloSecurityProto.SecurityHealthCheckResponse> getHealthCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HealthCheck",
      requestType = HelloSecurityProto.SecurityHealthCheckRequest.class,
      responseType = HelloSecurityProto.SecurityHealthCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<HelloSecurityProto.SecurityHealthCheckRequest,
      HelloSecurityProto.SecurityHealthCheckResponse> getHealthCheckMethod() {
    io.grpc.MethodDescriptor<HelloSecurityProto.SecurityHealthCheckRequest, HelloSecurityProto.SecurityHealthCheckResponse> getHealthCheckMethod;
    if ((getHealthCheckMethod = HelloSecurityGrpc.getHealthCheckMethod) == null) {
      synchronized (HelloSecurityGrpc.class) {
        if ((getHealthCheckMethod = HelloSecurityGrpc.getHealthCheckMethod) == null) {
          HelloSecurityGrpc.getHealthCheckMethod = getHealthCheckMethod =
              io.grpc.MethodDescriptor.<HelloSecurityProto.SecurityHealthCheckRequest, HelloSecurityProto.SecurityHealthCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HealthCheck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloSecurityProto.SecurityHealthCheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloSecurityProto.SecurityHealthCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloSecurityMethodDescriptorSupplier("HealthCheck"))
              .build();
        }
      }
    }
    return getHealthCheckMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloSecurityStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloSecurityStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloSecurityStub>() {
        @Override
        public HelloSecurityStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloSecurityStub(channel, callOptions);
        }
      };
    return HelloSecurityStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloSecurityBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloSecurityBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloSecurityBlockingStub>() {
        @Override
        public HelloSecurityBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloSecurityBlockingStub(channel, callOptions);
        }
      };
    return HelloSecurityBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloSecurityFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloSecurityFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloSecurityFutureStub>() {
        @Override
        public HelloSecurityFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloSecurityFutureStub(channel, callOptions);
        }
      };
    return HelloSecurityFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<HelloSecurityProto.SecurityRequest> securityService(
        io.grpc.stub.StreamObserver<HelloSecurityProto.SecurityResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSecurityServiceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server-side streaming RPC for health check
     * </pre>
     */
    default void healthCheck(HelloSecurityProto.SecurityHealthCheckRequest request,
                             io.grpc.stub.StreamObserver<HelloSecurityProto.SecurityHealthCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHealthCheckMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service HelloSecurity.
   */
  public static abstract class HelloSecurityImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return HelloSecurityGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service HelloSecurity.
   */
  public static final class HelloSecurityStub
      extends io.grpc.stub.AbstractAsyncStub<HelloSecurityStub> {
    private HelloSecurityStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloSecurityStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloSecurityStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<HelloSecurityProto.SecurityRequest> securityService(
        io.grpc.stub.StreamObserver<HelloSecurityProto.SecurityResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getSecurityServiceMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Server-side streaming RPC for health check
     * </pre>
     */
    public void healthCheck(HelloSecurityProto.SecurityHealthCheckRequest request,
                            io.grpc.stub.StreamObserver<HelloSecurityProto.SecurityHealthCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getHealthCheckMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service HelloSecurity.
   */
  public static final class HelloSecurityBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<HelloSecurityBlockingStub> {
    private HelloSecurityBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloSecurityBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloSecurityBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Server-side streaming RPC for health check
     * </pre>
     */
    public java.util.Iterator<HelloSecurityProto.SecurityHealthCheckResponse> healthCheck(
        HelloSecurityProto.SecurityHealthCheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getHealthCheckMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service HelloSecurity.
   */
  public static final class HelloSecurityFutureStub
      extends io.grpc.stub.AbstractFutureStub<HelloSecurityFutureStub> {
    private HelloSecurityFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloSecurityFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloSecurityFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_HEALTH_CHECK = 0;
  private static final int METHODID_SECURITY_SERVICE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HEALTH_CHECK:
          serviceImpl.healthCheck((HelloSecurityProto.SecurityHealthCheckRequest) request,
              (io.grpc.stub.StreamObserver<HelloSecurityProto.SecurityHealthCheckResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SECURITY_SERVICE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.securityService(
              (io.grpc.stub.StreamObserver<HelloSecurityProto.SecurityResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSecurityServiceMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              HelloSecurityProto.SecurityRequest,
              HelloSecurityProto.SecurityResponse>(
                service, METHODID_SECURITY_SERVICE)))
        .addMethod(
          getHealthCheckMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              HelloSecurityProto.SecurityHealthCheckRequest,
              HelloSecurityProto.SecurityHealthCheckResponse>(
                service, METHODID_HEALTH_CHECK)))
        .build();
  }

  private static abstract class HelloSecurityBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloSecurityBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return HelloSecurityProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloSecurity");
    }
  }

  private static final class HelloSecurityFileDescriptorSupplier
      extends HelloSecurityBaseDescriptorSupplier {
    HelloSecurityFileDescriptorSupplier() {}
  }

  private static final class HelloSecurityMethodDescriptorSupplier
      extends HelloSecurityBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloSecurityMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HelloSecurityGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloSecurityFileDescriptorSupplier())
              .addMethod(getSecurityServiceMethod())
              .addMethod(getHealthCheckMethod())
              .build();
        }
      }
    }
    return result;
  }
}
