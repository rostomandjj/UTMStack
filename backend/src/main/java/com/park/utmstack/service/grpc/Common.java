// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: common.proto
// Protobuf Java Version: 4.29.3

package com.park.utmstack.service.grpc;

public final class Common {
  private Common() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 3,
      /* suffix= */ "",
      Common.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_agent_ListRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_agent_ListRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_agent_AuthResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_agent_AuthResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_agent_Hostname_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_agent_Hostname_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014common.proto\022\005agent\"\\\n\013ListRequest\022\023\n\013" +
      "page_number\030\001 \001(\005\022\021\n\tpage_size\030\002 \001(\005\022\024\n\014" +
      "search_query\030\003 \001(\t\022\017\n\007sort_by\030\004 \001(\t\"\'\n\014A" +
      "uthResponse\022\n\n\002id\030\001 \001(\r\022\013\n\003key\030\002 \001(\t\"\034\n\010" +
      "Hostname\022\020\n\010hostname\030\001 \001(\t*.\n\006Status\022\n\n\006" +
      "ONLINE\020\000\022\013\n\007OFFLINE\020\001\022\013\n\007UNKNOWN\020\002*)\n\rCo" +
      "nnectorType\022\t\n\005AGENT\020\000\022\r\n\tCOLLECTOR\020\001B-\n" +
      "\036com.park.utmstack.service.grpcB\006CommonP" +
      "\001\210\001\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_agent_ListRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_agent_ListRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_agent_ListRequest_descriptor,
        new java.lang.String[] { "PageNumber", "PageSize", "SearchQuery", "SortBy", });
    internal_static_agent_AuthResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_agent_AuthResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_agent_AuthResponse_descriptor,
        new java.lang.String[] { "Id", "Key", });
    internal_static_agent_Hostname_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_agent_Hostname_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_agent_Hostname_descriptor,
        new java.lang.String[] { "Hostname", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
