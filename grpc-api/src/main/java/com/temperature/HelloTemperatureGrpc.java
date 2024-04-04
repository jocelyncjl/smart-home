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
  private static volatile io.grpc.MethodDescriptor<com.temperature.HelloTemperatureProto.TemperatureRequest,
      com.temperature.HelloTemperatureProto.TemperatureResponse> getTemperatureServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "temperatureService",
      requestType = com.temperature.HelloTemperatureProto.TemperatureRequest.class,
      responseType = com.temperature.HelloTemperatureProto.TemperatureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.temperature.HelloTemperatureProto.TemperatureRequest,
      com.temperature.HelloTemperatureProto.TemperatureResponse> getTemperatureServiceMethod() {
    io.grpc.MethodDescriptor<com.temperature.HelloTemperatureProto.TemperatureRequest, com.temperature.HelloTemperatureProto.TemperatureResponse> getTemperatureServiceMethod;
    if ((getTemperatureServiceMethod = HelloTemperatureGrpc.getTemperatureServiceMethod) == null) {
      synchronized (HelloTemperatureGrpc.class) {
        if ((getTemperatureServiceMethod = HelloTemperatureGrpc.getTemperatureServiceMethod) == null) {
          HelloTemperatureGrpc.getTemperatureServiceMethod = getTemperatureServiceMethod =
              io.grpc.MethodDescriptor.<com.temperature.HelloTemperatureProto.TemperatureRequest, com.temperature.HelloTemperatureProto.TemperatureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "temperatureService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.temperature.HelloTemperatureProto.TemperatureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.temperature.HelloTemperatureProto.TemperatureResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloTemperatureMethodDescriptorSupplier("temperatureService"))
              .build();
        }
      }
    }
    return getTemperatureServiceMethod;
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
    default void temperatureService(com.temperature.HelloTemperatureProto.TemperatureRequest request,
        io.grpc.stub.StreamObserver<com.temperature.HelloTemperatureProto.TemperatureResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTemperatureServiceMethod(), responseObserver);
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
    public void temperatureService(com.temperature.HelloTemperatureProto.TemperatureRequest request,
        io.grpc.stub.StreamObserver<com.temperature.HelloTemperatureProto.TemperatureResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTemperatureServiceMethod(), getCallOptions()), request, responseObserver);
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
    public com.temperature.HelloTemperatureProto.TemperatureResponse temperatureService(com.temperature.HelloTemperatureProto.TemperatureRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTemperatureServiceMethod(), getCallOptions(), request);
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

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.temperature.HelloTemperatureProto.TemperatureResponse> temperatureService(
        com.temperature.HelloTemperatureProto.TemperatureRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTemperatureServiceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TEMPERATURE_SERVICE = 0;

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
          serviceImpl.temperatureService((com.temperature.HelloTemperatureProto.TemperatureRequest) request,
              (io.grpc.stub.StreamObserver<com.temperature.HelloTemperatureProto.TemperatureResponse>) responseObserver);
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
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.temperature.HelloTemperatureProto.TemperatureRequest,
              com.temperature.HelloTemperatureProto.TemperatureResponse>(
                service, METHODID_TEMPERATURE_SERVICE)))
        .build();
  }

  private static abstract class HelloTemperatureBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloTemperatureBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.temperature.HelloTemperatureProto.getDescriptor();
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
              .build();
        }
      }
    }
    return result;
  }
}
