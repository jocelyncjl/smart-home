package com.temperature;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: Temperature.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HelloTemperatureGrpc {

  private HelloTemperatureGrpc() {}

  public static final String SERVICE_NAME = "HelloTemperature";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<HelloTemperatureProto.TemperatureRequest,
      HelloTemperatureProto.TemperatureResponse> getTemperatureServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "temperatureService",
      requestType = HelloTemperatureProto.TemperatureRequest.class,
      responseType = HelloTemperatureProto.TemperatureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<HelloTemperatureProto.TemperatureRequest,
      HelloTemperatureProto.TemperatureResponse> getTemperatureServiceMethod() {
    io.grpc.MethodDescriptor<HelloTemperatureProto.TemperatureRequest, HelloTemperatureProto.TemperatureResponse> getTemperatureServiceMethod;
    if ((getTemperatureServiceMethod = HelloTemperatureGrpc.getTemperatureServiceMethod) == null) {
      synchronized (HelloTemperatureGrpc.class) {
        if ((getTemperatureServiceMethod = HelloTemperatureGrpc.getTemperatureServiceMethod) == null) {
          HelloTemperatureGrpc.getTemperatureServiceMethod = getTemperatureServiceMethod =
              io.grpc.MethodDescriptor.<HelloTemperatureProto.TemperatureRequest, HelloTemperatureProto.TemperatureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "temperatureService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloTemperatureProto.TemperatureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloTemperatureProto.TemperatureResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloTemperatureMethodDescriptorSupplier("temperatureService"))
              .build();
        }
      }
    }
    return getTemperatureServiceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<HelloTemperatureProto.TempHealthCheckRequest,
      HelloTemperatureProto.TempHealthCheckResponse> getHealthCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HealthCheck",
      requestType = HelloTemperatureProto.TempHealthCheckRequest.class,
      responseType = HelloTemperatureProto.TempHealthCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<HelloTemperatureProto.TempHealthCheckRequest,
      HelloTemperatureProto.TempHealthCheckResponse> getHealthCheckMethod() {
    io.grpc.MethodDescriptor<HelloTemperatureProto.TempHealthCheckRequest, HelloTemperatureProto.TempHealthCheckResponse> getHealthCheckMethod;
    if ((getHealthCheckMethod = HelloTemperatureGrpc.getHealthCheckMethod) == null) {
      synchronized (HelloTemperatureGrpc.class) {
        if ((getHealthCheckMethod = HelloTemperatureGrpc.getHealthCheckMethod) == null) {
          HelloTemperatureGrpc.getHealthCheckMethod = getHealthCheckMethod =
              io.grpc.MethodDescriptor.<HelloTemperatureProto.TempHealthCheckRequest, HelloTemperatureProto.TempHealthCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HealthCheck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloTemperatureProto.TempHealthCheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloTemperatureProto.TempHealthCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloTemperatureMethodDescriptorSupplier("HealthCheck"))
              .build();
        }
      }
    }
    return getHealthCheckMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloTemperatureStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloTemperatureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloTemperatureStub>() {
        @Override
        public HelloTemperatureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloTemperatureStub(channel, callOptions);
        }
      };
    return HelloTemperatureStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloTemperatureBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloTemperatureBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloTemperatureBlockingStub>() {
        @Override
        public HelloTemperatureBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloTemperatureBlockingStub(channel, callOptions);
        }
      };
    return HelloTemperatureBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloTemperatureFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloTemperatureFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloTemperatureFutureStub>() {
        @Override
        public HelloTemperatureFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloTemperatureFutureStub(channel, callOptions);
        }
      };
    return HelloTemperatureFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void temperatureService(HelloTemperatureProto.TemperatureRequest request,
                                    io.grpc.stub.StreamObserver<HelloTemperatureProto.TemperatureResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTemperatureServiceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server-side streaming RPC for health check
     * </pre>
     */
    default void healthCheck(HelloTemperatureProto.TempHealthCheckRequest request,
                             io.grpc.stub.StreamObserver<HelloTemperatureProto.TempHealthCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHealthCheckMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service HelloTemperature.
   */
  public static abstract class HelloTemperatureImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return HelloTemperatureGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service HelloTemperature.
   */
  public static final class HelloTemperatureStub
      extends io.grpc.stub.AbstractAsyncStub<HelloTemperatureStub> {
    private HelloTemperatureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloTemperatureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloTemperatureStub(channel, callOptions);
    }

    /**
     */
    public void temperatureService(HelloTemperatureProto.TemperatureRequest request,
                                   io.grpc.stub.StreamObserver<HelloTemperatureProto.TemperatureResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getTemperatureServiceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server-side streaming RPC for health check
     * </pre>
     */
    public void healthCheck(HelloTemperatureProto.TempHealthCheckRequest request,
                            io.grpc.stub.StreamObserver<HelloTemperatureProto.TempHealthCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getHealthCheckMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service HelloTemperature.
   */
  public static final class HelloTemperatureBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<HelloTemperatureBlockingStub> {
    private HelloTemperatureBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloTemperatureBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloTemperatureBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<HelloTemperatureProto.TemperatureResponse> temperatureService(
        HelloTemperatureProto.TemperatureRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getTemperatureServiceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server-side streaming RPC for health check
     * </pre>
     */
    public java.util.Iterator<HelloTemperatureProto.TempHealthCheckResponse> healthCheck(
        HelloTemperatureProto.TempHealthCheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getHealthCheckMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service HelloTemperature.
   */
  public static final class HelloTemperatureFutureStub
      extends io.grpc.stub.AbstractFutureStub<HelloTemperatureFutureStub> {
    private HelloTemperatureFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloTemperatureFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloTemperatureFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_TEMPERATURE_SERVICE = 0;
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
        case METHODID_TEMPERATURE_SERVICE:
          serviceImpl.temperatureService((HelloTemperatureProto.TemperatureRequest) request,
              (io.grpc.stub.StreamObserver<HelloTemperatureProto.TemperatureResponse>) responseObserver);
          break;
        case METHODID_HEALTH_CHECK:
          serviceImpl.healthCheck((HelloTemperatureProto.TempHealthCheckRequest) request,
              (io.grpc.stub.StreamObserver<HelloTemperatureProto.TempHealthCheckResponse>) responseObserver);
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
          getTemperatureServiceMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              HelloTemperatureProto.TemperatureRequest,
              HelloTemperatureProto.TemperatureResponse>(
                service, METHODID_TEMPERATURE_SERVICE)))
        .addMethod(
          getHealthCheckMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              HelloTemperatureProto.TempHealthCheckRequest,
              HelloTemperatureProto.TempHealthCheckResponse>(
                service, METHODID_HEALTH_CHECK)))
        .build();
  }

  private static abstract class HelloTemperatureBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloTemperatureBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return HelloTemperatureProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloTemperature");
    }
  }

  private static final class HelloTemperatureFileDescriptorSupplier
      extends HelloTemperatureBaseDescriptorSupplier {
    HelloTemperatureFileDescriptorSupplier() {}
  }

  private static final class HelloTemperatureMethodDescriptorSupplier
      extends HelloTemperatureBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloTemperatureMethodDescriptorSupplier(String methodName) {
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
      synchronized (HelloTemperatureGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloTemperatureFileDescriptorSupplier())
              .addMethod(getTemperatureServiceMethod())
              .addMethod(getHealthCheckMethod())
              .build();
        }
      }
    }
    return result;
  }
}
