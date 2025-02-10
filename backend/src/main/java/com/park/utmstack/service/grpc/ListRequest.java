// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: common.proto
// Protobuf Java Version: 4.29.3

package com.park.utmstack.service.grpc;

/**
 * Protobuf type {@code agent.ListRequest}
 */
public final class ListRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:agent.ListRequest)
    ListRequestOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 3,
      /* suffix= */ "",
      ListRequest.class.getName());
  }
  // Use ListRequest.newBuilder() to construct.
  private ListRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private ListRequest() {
    searchQuery_ = "";
    sortBy_ = "";
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.park.utmstack.service.grpc.Common.internal_static_agent_ListRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.park.utmstack.service.grpc.Common.internal_static_agent_ListRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.park.utmstack.service.grpc.ListRequest.class, com.park.utmstack.service.grpc.ListRequest.Builder.class);
  }

  public static final int PAGE_NUMBER_FIELD_NUMBER = 1;
  private int pageNumber_ = 0;
  /**
   * <code>int32 page_number = 1;</code>
   * @return The pageNumber.
   */
  @java.lang.Override
  public int getPageNumber() {
    return pageNumber_;
  }

  public static final int PAGE_SIZE_FIELD_NUMBER = 2;
  private int pageSize_ = 0;
  /**
   * <code>int32 page_size = 2;</code>
   * @return The pageSize.
   */
  @java.lang.Override
  public int getPageSize() {
    return pageSize_;
  }

  public static final int SEARCH_QUERY_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile java.lang.Object searchQuery_ = "";
  /**
   * <code>string search_query = 3;</code>
   * @return The searchQuery.
   */
  @java.lang.Override
  public java.lang.String getSearchQuery() {
    java.lang.Object ref = searchQuery_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      searchQuery_ = s;
      return s;
    }
  }
  /**
   * <code>string search_query = 3;</code>
   * @return The bytes for searchQuery.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getSearchQueryBytes() {
    java.lang.Object ref = searchQuery_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      searchQuery_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SORT_BY_FIELD_NUMBER = 4;
  @SuppressWarnings("serial")
  private volatile java.lang.Object sortBy_ = "";
  /**
   * <code>string sort_by = 4;</code>
   * @return The sortBy.
   */
  @java.lang.Override
  public java.lang.String getSortBy() {
    java.lang.Object ref = sortBy_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      sortBy_ = s;
      return s;
    }
  }
  /**
   * <code>string sort_by = 4;</code>
   * @return The bytes for sortBy.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getSortByBytes() {
    java.lang.Object ref = sortBy_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      sortBy_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (pageNumber_ != 0) {
      output.writeInt32(1, pageNumber_);
    }
    if (pageSize_ != 0) {
      output.writeInt32(2, pageSize_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(searchQuery_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 3, searchQuery_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sortBy_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 4, sortBy_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (pageNumber_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, pageNumber_);
    }
    if (pageSize_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, pageSize_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(searchQuery_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(3, searchQuery_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sortBy_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(4, sortBy_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.park.utmstack.service.grpc.ListRequest)) {
      return super.equals(obj);
    }
    com.park.utmstack.service.grpc.ListRequest other = (com.park.utmstack.service.grpc.ListRequest) obj;

    if (getPageNumber()
        != other.getPageNumber()) return false;
    if (getPageSize()
        != other.getPageSize()) return false;
    if (!getSearchQuery()
        .equals(other.getSearchQuery())) return false;
    if (!getSortBy()
        .equals(other.getSortBy())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PAGE_NUMBER_FIELD_NUMBER;
    hash = (53 * hash) + getPageNumber();
    hash = (37 * hash) + PAGE_SIZE_FIELD_NUMBER;
    hash = (53 * hash) + getPageSize();
    hash = (37 * hash) + SEARCH_QUERY_FIELD_NUMBER;
    hash = (53 * hash) + getSearchQuery().hashCode();
    hash = (37 * hash) + SORT_BY_FIELD_NUMBER;
    hash = (53 * hash) + getSortBy().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.park.utmstack.service.grpc.ListRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.park.utmstack.service.grpc.ListRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.park.utmstack.service.grpc.ListRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.park.utmstack.service.grpc.ListRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.park.utmstack.service.grpc.ListRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.park.utmstack.service.grpc.ListRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.park.utmstack.service.grpc.ListRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.park.utmstack.service.grpc.ListRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.park.utmstack.service.grpc.ListRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.park.utmstack.service.grpc.ListRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.park.utmstack.service.grpc.ListRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.park.utmstack.service.grpc.ListRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.park.utmstack.service.grpc.ListRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code agent.ListRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:agent.ListRequest)
      com.park.utmstack.service.grpc.ListRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.park.utmstack.service.grpc.Common.internal_static_agent_ListRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.park.utmstack.service.grpc.Common.internal_static_agent_ListRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.park.utmstack.service.grpc.ListRequest.class, com.park.utmstack.service.grpc.ListRequest.Builder.class);
    }

    // Construct using com.park.utmstack.service.grpc.ListRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      pageNumber_ = 0;
      pageSize_ = 0;
      searchQuery_ = "";
      sortBy_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.park.utmstack.service.grpc.Common.internal_static_agent_ListRequest_descriptor;
    }

    @java.lang.Override
    public com.park.utmstack.service.grpc.ListRequest getDefaultInstanceForType() {
      return com.park.utmstack.service.grpc.ListRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.park.utmstack.service.grpc.ListRequest build() {
      com.park.utmstack.service.grpc.ListRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.park.utmstack.service.grpc.ListRequest buildPartial() {
      com.park.utmstack.service.grpc.ListRequest result = new com.park.utmstack.service.grpc.ListRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.park.utmstack.service.grpc.ListRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.pageNumber_ = pageNumber_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.pageSize_ = pageSize_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.searchQuery_ = searchQuery_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.sortBy_ = sortBy_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.park.utmstack.service.grpc.ListRequest) {
        return mergeFrom((com.park.utmstack.service.grpc.ListRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.park.utmstack.service.grpc.ListRequest other) {
      if (other == com.park.utmstack.service.grpc.ListRequest.getDefaultInstance()) return this;
      if (other.getPageNumber() != 0) {
        setPageNumber(other.getPageNumber());
      }
      if (other.getPageSize() != 0) {
        setPageSize(other.getPageSize());
      }
      if (!other.getSearchQuery().isEmpty()) {
        searchQuery_ = other.searchQuery_;
        bitField0_ |= 0x00000004;
        onChanged();
      }
      if (!other.getSortBy().isEmpty()) {
        sortBy_ = other.sortBy_;
        bitField0_ |= 0x00000008;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
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
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              pageNumber_ = input.readInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              pageSize_ = input.readInt32();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 26: {
              searchQuery_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            case 34: {
              sortBy_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000008;
              break;
            } // case 34
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int pageNumber_ ;
    /**
     * <code>int32 page_number = 1;</code>
     * @return The pageNumber.
     */
    @java.lang.Override
    public int getPageNumber() {
      return pageNumber_;
    }
    /**
     * <code>int32 page_number = 1;</code>
     * @param value The pageNumber to set.
     * @return This builder for chaining.
     */
    public Builder setPageNumber(int value) {

      pageNumber_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int32 page_number = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPageNumber() {
      bitField0_ = (bitField0_ & ~0x00000001);
      pageNumber_ = 0;
      onChanged();
      return this;
    }

    private int pageSize_ ;
    /**
     * <code>int32 page_size = 2;</code>
     * @return The pageSize.
     */
    @java.lang.Override
    public int getPageSize() {
      return pageSize_;
    }
    /**
     * <code>int32 page_size = 2;</code>
     * @param value The pageSize to set.
     * @return This builder for chaining.
     */
    public Builder setPageSize(int value) {

      pageSize_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>int32 page_size = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPageSize() {
      bitField0_ = (bitField0_ & ~0x00000002);
      pageSize_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object searchQuery_ = "";
    /**
     * <code>string search_query = 3;</code>
     * @return The searchQuery.
     */
    public java.lang.String getSearchQuery() {
      java.lang.Object ref = searchQuery_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        searchQuery_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string search_query = 3;</code>
     * @return The bytes for searchQuery.
     */
    public com.google.protobuf.ByteString
        getSearchQueryBytes() {
      java.lang.Object ref = searchQuery_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        searchQuery_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string search_query = 3;</code>
     * @param value The searchQuery to set.
     * @return This builder for chaining.
     */
    public Builder setSearchQuery(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      searchQuery_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>string search_query = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearSearchQuery() {
      searchQuery_ = getDefaultInstance().getSearchQuery();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>string search_query = 3;</code>
     * @param value The bytes for searchQuery to set.
     * @return This builder for chaining.
     */
    public Builder setSearchQueryBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      searchQuery_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }

    private java.lang.Object sortBy_ = "";
    /**
     * <code>string sort_by = 4;</code>
     * @return The sortBy.
     */
    public java.lang.String getSortBy() {
      java.lang.Object ref = sortBy_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        sortBy_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string sort_by = 4;</code>
     * @return The bytes for sortBy.
     */
    public com.google.protobuf.ByteString
        getSortByBytes() {
      java.lang.Object ref = sortBy_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        sortBy_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string sort_by = 4;</code>
     * @param value The sortBy to set.
     * @return This builder for chaining.
     */
    public Builder setSortBy(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      sortBy_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>string sort_by = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearSortBy() {
      sortBy_ = getDefaultInstance().getSortBy();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }
    /**
     * <code>string sort_by = 4;</code>
     * @param value The bytes for sortBy to set.
     * @return This builder for chaining.
     */
    public Builder setSortByBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      sortBy_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:agent.ListRequest)
  }

  // @@protoc_insertion_point(class_scope:agent.ListRequest)
  private static final com.park.utmstack.service.grpc.ListRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.park.utmstack.service.grpc.ListRequest();
  }

  public static com.park.utmstack.service.grpc.ListRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ListRequest>
      PARSER = new com.google.protobuf.AbstractParser<ListRequest>() {
    @java.lang.Override
    public ListRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<ListRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ListRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.park.utmstack.service.grpc.ListRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

