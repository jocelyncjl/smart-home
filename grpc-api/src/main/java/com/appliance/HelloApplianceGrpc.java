package com.appliance;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: Appliance.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HelloApplianceGrpc {

  private HelloApplianceGrpc() {}

  public static final String SERVICE_NAME = "HelloAppliance";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<HelloApplianceProto.ApplianceRequest,
      HelloApplianceProto.ApplianceResponse> getApplianceServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "applianceService",
      requestType = HelloApplianceProto.ApplianceRequest.class,
      responseType = HelloApplianceProto.ApplianceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<HelloApplianceProto.ApplianceRequest,
      HelloApplianceProto.ApplianceResponse> getApplianceServiceMethod() {
    io.grpc.MethodDescriptor<HelloApplianceProto.ApplianceRequest, HelloApplianceProto.ApplianceResponse> getApplianceServiceMethod;
    if ((getApplianceServiceMethod = HelloApplianceGrpc.getApplianceServiceMethod) == null) {
      synchronized (HelloApplianceGrpc.class) {
        if ((getApplianceServiceMethod = HelloApplianceGrpc.getApplianceServiceMethod) == null) {
          HelloApplianceGrpc.getApplianceServiceMethod = getApplianceServiceMethod =
              io.grpc.MethodDescriptor.<HelloApplianceProto.ApplianceRequest, HelloApplianceProto.ApplianceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "applianceService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloApplianceProto.ApplianceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloApplianceProto.ApplianceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloApplianceMethodDescriptorSupplier("applianceService"))
              .build();
        }
      }
    }
    return getApplianceServiceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloApplianceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloApplianceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloApplianceStub>() {
        @Override
        public HelloApplianceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloApplianceStub(channel, callOptions);
        }
      };
    return HelloApplianceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloApplianceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloApplianceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloApplianceBlockingStub>() {
        @Override
        public HelloApplianceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloApplianceBlockingStub(channel, callOptions);
        }
      };
    return HelloApplianceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloApplianceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloApplianceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloApplianceFutureStub>() {
        @Override
        public HelloApplianceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloApplianceFutureStub(channel, callOptions);
        }
      };
    return HelloApplianceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<HelloApplianceProto.ApplianceRequest> applianceService(
        io.grpc.stub.StreamObserver<HelloApplianceProto.ApplianceResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getApplianceServiceMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service HelloAppliance.
   */
  public static abstract class HelloApplianceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return HelloApplianceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service HelloAppliance.
   */
  public static final class HelloApplianceStub
      extends io.grpc.stub.AbstractAsyncStub<HelloApplianceStub> {
    private HelloApplianceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloApplianceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloApplianceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<HelloApplianceProto.ApplianceRequest> applianceService(
        io.grpc.stub.StreamObserver<HelloApplianceProto.ApplianceResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getApplianceServiceMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service HelloAppliance.
   */
  public static final class HelloApplianceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<HelloApplianceBlockingStub> {
    private HelloApplianceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloApplianceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloApplianceBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service HelloAppliance.
   */
  public static final class HelloApplianceFutureStub
      extends io.grpc.stub.AbstractFutureStub<HelloApplianceFutureStub> {
    private HelloApplianceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloApplianceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloApplianceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_APPLIANCE_SERVICE = 0;

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
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_APPLIANCE_SERVICE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.applianceService(
              (io.grpc.stub.StreamObserver<HelloApplianceProto.ApplianceResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getApplianceServiceMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              HelloApplianceProto.ApplianceRequest,
              HelloApplianceProto.ApplianceResponse>(
                service, METHODID_APPLIANCE_SERVICE)))
        .build();
  }

  private static abstract class HelloApplianceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloApplianceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return HelloApplianceProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloAppliance");
    }
  }

  private static final class HelloApplianceFileDescriptorSupplier
      extends HelloApplianceBaseDescriptorSupplier {
    HelloApplianceFileDescriptorSupplier() {}
  }

  private static final class HelloApplianceMethodDescriptorSupplier
      extends HelloApplianceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloApplianceMethodDescriptorSupplier(String methodName) {
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
      synchronized (HelloApplianceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloApplianceFileDescriptorSupplier())
              .addMethod(getApplianceServiceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
