// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RemoteUserService.proto

package org.otus.education.hw17.front.protobuf.generated;

/**
 * Protobuf type {@code org.otus.education.hw17.front.protobuf.generated.Empty}
 */
public final class Empty extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:org.otus.education.hw17.front.protobuf.generated.Empty)
    EmptyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Empty.newBuilder() to construct.
  private Empty(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Empty() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Empty();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Empty(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.otus.education.hw17.front.protobuf.generated.RemoteUserServiceOuterClass.internal_static_org_otus_education_hw17_front_protobuf_generated_Empty_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.otus.education.hw17.front.protobuf.generated.RemoteUserServiceOuterClass.internal_static_org_otus_education_hw17_front_protobuf_generated_Empty_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.otus.education.hw17.front.protobuf.generated.Empty.class, org.otus.education.hw17.front.protobuf.generated.Empty.Builder.class);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.otus.education.hw17.front.protobuf.generated.Empty)) {
      return super.equals(obj);
    }
    org.otus.education.hw17.front.protobuf.generated.Empty other = (org.otus.education.hw17.front.protobuf.generated.Empty) obj;

    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.otus.education.hw17.front.protobuf.generated.Empty parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.otus.education.hw17.front.protobuf.generated.Empty prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code org.otus.education.hw17.front.protobuf.generated.Empty}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:org.otus.education.hw17.front.protobuf.generated.Empty)
      org.otus.education.hw17.front.protobuf.generated.EmptyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.otus.education.hw17.front.protobuf.generated.RemoteUserServiceOuterClass.internal_static_org_otus_education_hw17_front_protobuf_generated_Empty_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.otus.education.hw17.front.protobuf.generated.RemoteUserServiceOuterClass.internal_static_org_otus_education_hw17_front_protobuf_generated_Empty_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.otus.education.hw17.front.protobuf.generated.Empty.class, org.otus.education.hw17.front.protobuf.generated.Empty.Builder.class);
    }

    // Construct using org.otus.education.hw17.front.protobuf.generated.Empty.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.otus.education.hw17.front.protobuf.generated.RemoteUserServiceOuterClass.internal_static_org_otus_education_hw17_front_protobuf_generated_Empty_descriptor;
    }

    @java.lang.Override
    public org.otus.education.hw17.front.protobuf.generated.Empty getDefaultInstanceForType() {
      return org.otus.education.hw17.front.protobuf.generated.Empty.getDefaultInstance();
    }

    @java.lang.Override
    public org.otus.education.hw17.front.protobuf.generated.Empty build() {
      org.otus.education.hw17.front.protobuf.generated.Empty result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.otus.education.hw17.front.protobuf.generated.Empty buildPartial() {
      org.otus.education.hw17.front.protobuf.generated.Empty result = new org.otus.education.hw17.front.protobuf.generated.Empty(this);
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.otus.education.hw17.front.protobuf.generated.Empty) {
        return mergeFrom((org.otus.education.hw17.front.protobuf.generated.Empty)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.otus.education.hw17.front.protobuf.generated.Empty other) {
      if (other == org.otus.education.hw17.front.protobuf.generated.Empty.getDefaultInstance()) return this;
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.otus.education.hw17.front.protobuf.generated.Empty parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.otus.education.hw17.front.protobuf.generated.Empty) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:org.otus.education.hw17.front.protobuf.generated.Empty)
  }

  // @@protoc_insertion_point(class_scope:org.otus.education.hw17.front.protobuf.generated.Empty)
  private static final org.otus.education.hw17.front.protobuf.generated.Empty DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.otus.education.hw17.front.protobuf.generated.Empty();
  }

  public static org.otus.education.hw17.front.protobuf.generated.Empty getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Empty>
      PARSER = new com.google.protobuf.AbstractParser<Empty>() {
    @java.lang.Override
    public Empty parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Empty(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Empty> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Empty> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.otus.education.hw17.front.protobuf.generated.Empty getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
