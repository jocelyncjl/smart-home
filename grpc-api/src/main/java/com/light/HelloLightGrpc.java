package com.light;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: Light.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HelloLightGrpc {

  private HelloLightGrpc() {}

  public static final String SERVICE_NAME = "HelloLight";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<HelloLightProto.LightRequest,
      HelloLightProto.LightResponse> getLightServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lightService",
      requestType = HelloLightProto.LightRequest.class,
      responseType = HelloLightProto.LightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<HelloLightProto.LightRequest,
      HelloLightProto.LightResponse> getLightServiceMethod() {
    io.grpc.MethodDescriptor<HelloLightProto.LightRequest, HelloLightProto.LightResponse> getLightServiceMethod;
    if ((getLightServiceMethod = HelloLightGrpc.getLightServiceMethod) == null) {
      synchronized (HelloLightGrpc.class) {
        if ((getLightServiceMethod = HelloLightGrpc.getLightServiceMethod) == null) {
          HelloLightGrpc.getLightServiceMethod = getLightServiceMethod =
              io.grpc.MethodDescriptor.<HelloLightProto.LightRequest, HelloLightProto.LightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "lightService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloLightProto.LightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloLightProto.LightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloLightMethodDescriptorSupplier("lightService"))
              .build();
        }
      }
    }
    return getLightServiceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<HelloLightProto.HealthCheckRequest,
      HelloLightProto.HealthCheckResponse> getHealthCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HealthCheck",
      requestType = HelloLightProto.HealthCheckRequest.class,
      responseType = HelloLightProto.HealthCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<HelloLightProto.HealthCheckRequest,
      HelloLightProto.HealthCheckResponse> getHealthCheckMethod() {
    io.grpc.MethodDescriptor<HelloLightProto.HealthCheckRequest, HelloLightProto.HealthCheckResponse> getHealthCheckMethod;
    if ((getHealthCheckMethod = HelloLightGrpc.getHealthCheckMethod) == null) {
      synchronized (HelloLightGrpc.class) {
        if ((getHealthCheckMethod = HelloLightGrpc.getHealthCheckMethod) == null) {
          HelloLightGrpc.getHealthCheckMethod = getHealthCheckMethod =
              io.grpc.MethodDescriptor.<HelloLightProto.HealthCheckRequest, HelloLightProto.HealthCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HealthCheck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloLightProto.HealthCheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloLightProto.HealthCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloLightMethodDescriptorSupplier("HealthCheck"))
              .build();
        }
      }
    }
    return getHealthCheckMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloLightStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloLightStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloLightStub>() {
        @Override
        public HelloLightStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloLightStub(channel, callOptions);
        }
      };
    return HelloLightStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloLightBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloLightBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloLightBlockingStub>() {
        @Override
        public HelloLightBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloLightBlockingStub(channel, callOptions);
        }
      };
    return HelloLightBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloLightFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloLightFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloLightFutureStub>() {
        @Override
        public HelloLightFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloLightFutureStub(channel, callOptions);
        }
      };
    return HelloLightFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void lightService(HelloLightProto.LightRequest request,
                              io.grpc.stub.StreamObserver<HelloLightProto.LightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLightServiceMethod(), responseObserver);
    }

    /**
     */
    default void healthCheck(HelloLightProto.HealthCheckRequest request,
                             io.grpc.stub.StreamObserver<HelloLightProto.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHealthCheckMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service HelloLight.
   */
  public static abstract class HelloLightImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return HelloLightGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service HelloLight.
   */
  public static final class HelloLightStub
      extends io.grpc.stub.AbstractAsyncStub<HelloLightStub> {
    private HelloLightStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloLightStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloLightStub(channel, callOptions);
    }

    /**
     */
    public void lightService(HelloLightProto.LightRequest request,
                             io.grpc.stub.StreamObserver<HelloLightProto.LightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLightServiceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void healthCheck(HelloLightProto.HealthCheckRequest request,
                            io.grpc.stub.StreamObserver<HelloLightProto.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getHealthCheckMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service HelloLight.
   */
  public static final class HelloLightBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<HelloLightBlockingStub> {
    private HelloLightBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloLightBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloLightBlockingStub(channel, callOptions);
    }

    /**
     */
    public HelloLightProto.LightResponse lightService(HelloLightProto.LightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLightServiceMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<HelloLightProto.HealthCheckResponse> healthCheck(
        HelloLightProto.HealthCheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getHealthCheckMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service HelloLight.
   */
  public static final class HelloLightFutureStub
      extends io.grpc.stub.AbstractFutureStub<HelloLightFutureStub> {
    private HelloLightFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloLightFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloLightFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<HelloLightProto.LightResponse> lightService(
        HelloLightProto.LightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLightServiceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIGHT_SERVICE = 0;
  private static final int METHODID_HEALTH_CHECK = 1;

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
        case METHODID_LIGHT_SERVICE:
          serviceImpl.lightService((HelloLightProto.LightRequest) request,
              (io.grpc.stub.StreamObserver<HelloLightProto.LightResponse>) responseObserver);
          break;
        case METHODID_HEALTH_CHECK:
          serviceImpl.healthCheck((HelloLightProto.HealthCheckRequest) request,
              (io.grpc.stub.StreamObserver<HelloLightProto.HealthCheckResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getLightServiceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              HelloLightProto.LightRequest,
              HelloLightProto.LightResponse>(
                service, METHODID_LIGHT_SERVICE)))
        .addMethod(
          getHealthCheckMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              HelloLightProto.HealthCheckRequest,
              HelloLightProto.HealthCheckResponse>(
                service, METHODID_HEALTH_CHECK)))
        .build();
  }

  private static abstract class HelloLightBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloLightBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return HelloLightProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloLight");
    }
  }

  private static final class HelloLightFileDescriptorSupplier
      extends HelloLightBaseDescriptorSupplier {
    HelloLightFileDescriptorSupplier() {}
  }

  private static final class HelloLightMethodDescriptorSupplier
      extends HelloLightBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloLightMethodDescriptorSupplier(String methodName) {
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
      synchronized (HelloLightGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloLightFileDescriptorSupplier())
              .addMethod(getLightServiceMethod())
              .addMethod(getHealthCheckMethod())
              .build();
        }
      }
    }
    return result;
  }
}
