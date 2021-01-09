package org.otus.education.hw17.front.protobuf.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.30.2)",
    comments = "Source: RemoteUserService.proto")
public final class RemoteUserServiceGrpc {

  private RemoteUserServiceGrpc() {}

  public static final String SERVICE_NAME = "org.otus.education.hw17.front.protobuf.generated.RemoteUserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.otus.education.hw17.front.protobuf.generated.UserIdQuery,
      org.otus.education.hw17.front.protobuf.generated.UserMessage> getGetUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUser",
      requestType = org.otus.education.hw17.front.protobuf.generated.UserIdQuery.class,
      responseType = org.otus.education.hw17.front.protobuf.generated.UserMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.otus.education.hw17.front.protobuf.generated.UserIdQuery,
      org.otus.education.hw17.front.protobuf.generated.UserMessage> getGetUserMethod() {
    io.grpc.MethodDescriptor<org.otus.education.hw17.front.protobuf.generated.UserIdQuery, org.otus.education.hw17.front.protobuf.generated.UserMessage> getGetUserMethod;
    if ((getGetUserMethod = RemoteUserServiceGrpc.getGetUserMethod) == null) {
      synchronized (RemoteUserServiceGrpc.class) {
        if ((getGetUserMethod = RemoteUserServiceGrpc.getGetUserMethod) == null) {
          RemoteUserServiceGrpc.getGetUserMethod = getGetUserMethod =
              io.grpc.MethodDescriptor.<org.otus.education.hw17.front.protobuf.generated.UserIdQuery, org.otus.education.hw17.front.protobuf.generated.UserMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.otus.education.hw17.front.protobuf.generated.UserIdQuery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.otus.education.hw17.front.protobuf.generated.UserMessage.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteUserServiceMethodDescriptorSupplier("getUser"))
              .build();
        }
      }
    }
    return getGetUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.otus.education.hw17.front.protobuf.generated.Empty,
      org.otus.education.hw17.front.protobuf.generated.UserMessage> getFindAllUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findAllUsers",
      requestType = org.otus.education.hw17.front.protobuf.generated.Empty.class,
      responseType = org.otus.education.hw17.front.protobuf.generated.UserMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.otus.education.hw17.front.protobuf.generated.Empty,
      org.otus.education.hw17.front.protobuf.generated.UserMessage> getFindAllUsersMethod() {
    io.grpc.MethodDescriptor<org.otus.education.hw17.front.protobuf.generated.Empty, org.otus.education.hw17.front.protobuf.generated.UserMessage> getFindAllUsersMethod;
    if ((getFindAllUsersMethod = RemoteUserServiceGrpc.getFindAllUsersMethod) == null) {
      synchronized (RemoteUserServiceGrpc.class) {
        if ((getFindAllUsersMethod = RemoteUserServiceGrpc.getFindAllUsersMethod) == null) {
          RemoteUserServiceGrpc.getFindAllUsersMethod = getFindAllUsersMethod =
              io.grpc.MethodDescriptor.<org.otus.education.hw17.front.protobuf.generated.Empty, org.otus.education.hw17.front.protobuf.generated.UserMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findAllUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.otus.education.hw17.front.protobuf.generated.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.otus.education.hw17.front.protobuf.generated.UserMessage.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteUserServiceMethodDescriptorSupplier("findAllUsers"))
              .build();
        }
      }
    }
    return getFindAllUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.otus.education.hw17.front.protobuf.generated.UserMessage,
      org.otus.education.hw17.front.protobuf.generated.UserMessage> getSaveUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "saveUser",
      requestType = org.otus.education.hw17.front.protobuf.generated.UserMessage.class,
      responseType = org.otus.education.hw17.front.protobuf.generated.UserMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.otus.education.hw17.front.protobuf.generated.UserMessage,
      org.otus.education.hw17.front.protobuf.generated.UserMessage> getSaveUserMethod() {
    io.grpc.MethodDescriptor<org.otus.education.hw17.front.protobuf.generated.UserMessage, org.otus.education.hw17.front.protobuf.generated.UserMessage> getSaveUserMethod;
    if ((getSaveUserMethod = RemoteUserServiceGrpc.getSaveUserMethod) == null) {
      synchronized (RemoteUserServiceGrpc.class) {
        if ((getSaveUserMethod = RemoteUserServiceGrpc.getSaveUserMethod) == null) {
          RemoteUserServiceGrpc.getSaveUserMethod = getSaveUserMethod =
              io.grpc.MethodDescriptor.<org.otus.education.hw17.front.protobuf.generated.UserMessage, org.otus.education.hw17.front.protobuf.generated.UserMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "saveUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.otus.education.hw17.front.protobuf.generated.UserMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.otus.education.hw17.front.protobuf.generated.UserMessage.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteUserServiceMethodDescriptorSupplier("saveUser"))
              .build();
        }
      }
    }
    return getSaveUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RemoteUserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteUserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteUserServiceStub>() {
        @java.lang.Override
        public RemoteUserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteUserServiceStub(channel, callOptions);
        }
      };
    return RemoteUserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RemoteUserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteUserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteUserServiceBlockingStub>() {
        @java.lang.Override
        public RemoteUserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteUserServiceBlockingStub(channel, callOptions);
        }
      };
    return RemoteUserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RemoteUserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteUserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteUserServiceFutureStub>() {
        @java.lang.Override
        public RemoteUserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteUserServiceFutureStub(channel, callOptions);
        }
      };
    return RemoteUserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RemoteUserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getUser(org.otus.education.hw17.front.protobuf.generated.UserIdQuery request,
        io.grpc.stub.StreamObserver<org.otus.education.hw17.front.protobuf.generated.UserMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserMethod(), responseObserver);
    }

    /**
     */
    public void findAllUsers(org.otus.education.hw17.front.protobuf.generated.Empty request,
        io.grpc.stub.StreamObserver<org.otus.education.hw17.front.protobuf.generated.UserMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getFindAllUsersMethod(), responseObserver);
    }

    /**
     */
    public void saveUser(org.otus.education.hw17.front.protobuf.generated.UserMessage request,
        io.grpc.stub.StreamObserver<org.otus.education.hw17.front.protobuf.generated.UserMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getSaveUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.otus.education.hw17.front.protobuf.generated.UserIdQuery,
                org.otus.education.hw17.front.protobuf.generated.UserMessage>(
                  this, METHODID_GET_USER)))
          .addMethod(
            getFindAllUsersMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                org.otus.education.hw17.front.protobuf.generated.Empty,
                org.otus.education.hw17.front.protobuf.generated.UserMessage>(
                  this, METHODID_FIND_ALL_USERS)))
          .addMethod(
            getSaveUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.otus.education.hw17.front.protobuf.generated.UserMessage,
                org.otus.education.hw17.front.protobuf.generated.UserMessage>(
                  this, METHODID_SAVE_USER)))
          .build();
    }
  }

  /**
   */
  public static final class RemoteUserServiceStub extends io.grpc.stub.AbstractAsyncStub<RemoteUserServiceStub> {
    private RemoteUserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteUserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteUserServiceStub(channel, callOptions);
    }

    /**
     */
    public void getUser(org.otus.education.hw17.front.protobuf.generated.UserIdQuery request,
        io.grpc.stub.StreamObserver<org.otus.education.hw17.front.protobuf.generated.UserMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findAllUsers(org.otus.education.hw17.front.protobuf.generated.Empty request,
        io.grpc.stub.StreamObserver<org.otus.education.hw17.front.protobuf.generated.UserMessage> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFindAllUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void saveUser(org.otus.education.hw17.front.protobuf.generated.UserMessage request,
        io.grpc.stub.StreamObserver<org.otus.education.hw17.front.protobuf.generated.UserMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSaveUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RemoteUserServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<RemoteUserServiceBlockingStub> {
    private RemoteUserServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteUserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteUserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.otus.education.hw17.front.protobuf.generated.UserMessage getUser(org.otus.education.hw17.front.protobuf.generated.UserIdQuery request) {
      return blockingUnaryCall(
          getChannel(), getGetUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<org.otus.education.hw17.front.protobuf.generated.UserMessage> findAllUsers(
        org.otus.education.hw17.front.protobuf.generated.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getFindAllUsersMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.otus.education.hw17.front.protobuf.generated.UserMessage saveUser(org.otus.education.hw17.front.protobuf.generated.UserMessage request) {
      return blockingUnaryCall(
          getChannel(), getSaveUserMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RemoteUserServiceFutureStub extends io.grpc.stub.AbstractFutureStub<RemoteUserServiceFutureStub> {
    private RemoteUserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteUserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteUserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.otus.education.hw17.front.protobuf.generated.UserMessage> getUser(
        org.otus.education.hw17.front.protobuf.generated.UserIdQuery request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.otus.education.hw17.front.protobuf.generated.UserMessage> saveUser(
        org.otus.education.hw17.front.protobuf.generated.UserMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getSaveUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_USER = 0;
  private static final int METHODID_FIND_ALL_USERS = 1;
  private static final int METHODID_SAVE_USER = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RemoteUserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RemoteUserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER:
          serviceImpl.getUser((org.otus.education.hw17.front.protobuf.generated.UserIdQuery) request,
              (io.grpc.stub.StreamObserver<org.otus.education.hw17.front.protobuf.generated.UserMessage>) responseObserver);
          break;
        case METHODID_FIND_ALL_USERS:
          serviceImpl.findAllUsers((org.otus.education.hw17.front.protobuf.generated.Empty) request,
              (io.grpc.stub.StreamObserver<org.otus.education.hw17.front.protobuf.generated.UserMessage>) responseObserver);
          break;
        case METHODID_SAVE_USER:
          serviceImpl.saveUser((org.otus.education.hw17.front.protobuf.generated.UserMessage) request,
              (io.grpc.stub.StreamObserver<org.otus.education.hw17.front.protobuf.generated.UserMessage>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RemoteUserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RemoteUserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.otus.education.hw17.front.protobuf.generated.RemoteUserServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RemoteUserService");
    }
  }

  private static final class RemoteUserServiceFileDescriptorSupplier
      extends RemoteUserServiceBaseDescriptorSupplier {
    RemoteUserServiceFileDescriptorSupplier() {}
  }

  private static final class RemoteUserServiceMethodDescriptorSupplier
      extends RemoteUserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RemoteUserServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RemoteUserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RemoteUserServiceFileDescriptorSupplier())
              .addMethod(getGetUserMethod())
              .addMethod(getFindAllUsersMethod())
              .addMethod(getSaveUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
