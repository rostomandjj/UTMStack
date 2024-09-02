// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: agent.proto

package com.park.utmstack.service.grpc;

public interface AgentOrBuilder extends
    // @@protoc_insertion_point(interface_extends:agent.Agent)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string ip = 1;</code>
   * @return The ip.
   */
  java.lang.String getIp();
  /**
   * <code>string ip = 1;</code>
   * @return The bytes for ip.
   */
  com.google.protobuf.ByteString
      getIpBytes();

  /**
   * <code>string hostname = 2;</code>
   * @return The hostname.
   */
  java.lang.String getHostname();
  /**
   * <code>string hostname = 2;</code>
   * @return The bytes for hostname.
   */
  com.google.protobuf.ByteString
      getHostnameBytes();

  /**
   * <code>string os = 3;</code>
   * @return The os.
   */
  java.lang.String getOs();
  /**
   * <code>string os = 3;</code>
   * @return The bytes for os.
   */
  com.google.protobuf.ByteString
      getOsBytes();

  /**
   * <code>.agent.Status status = 4;</code>
   * @return The enum numeric value on the wire for status.
   */
  int getStatusValue();
  /**
   * <code>.agent.Status status = 4;</code>
   * @return The status.
   */
  agent.Common.Status getStatus();

  /**
   * <code>string platform = 5;</code>
   * @return The platform.
   */
  java.lang.String getPlatform();
  /**
   * <code>string platform = 5;</code>
   * @return The bytes for platform.
   */
  com.google.protobuf.ByteString
      getPlatformBytes();

  /**
   * <code>string version = 6;</code>
   * @return The version.
   */
  java.lang.String getVersion();
  /**
   * <code>string version = 6;</code>
   * @return The bytes for version.
   */
  com.google.protobuf.ByteString
      getVersionBytes();

  /**
   * <code>string agent_key = 7;</code>
   * @return The agentKey.
   */
  java.lang.String getAgentKey();
  /**
   * <code>string agent_key = 7;</code>
   * @return The bytes for agentKey.
   */
  com.google.protobuf.ByteString
      getAgentKeyBytes();

  /**
   * <code>uint32 id = 8;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string last_seen = 9;</code>
   * @return The lastSeen.
   */
  java.lang.String getLastSeen();
  /**
   * <code>string last_seen = 9;</code>
   * @return The bytes for lastSeen.
   */
  com.google.protobuf.ByteString
      getLastSeenBytes();

  /**
   * <code>string mac = 10;</code>
   * @return The mac.
   */
  java.lang.String getMac();
  /**
   * <code>string mac = 10;</code>
   * @return The bytes for mac.
   */
  com.google.protobuf.ByteString
      getMacBytes();

  /**
   * <code>string os_major_version = 11;</code>
   * @return The osMajorVersion.
   */
  java.lang.String getOsMajorVersion();
  /**
   * <code>string os_major_version = 11;</code>
   * @return The bytes for osMajorVersion.
   */
  com.google.protobuf.ByteString
      getOsMajorVersionBytes();

  /**
   * <code>string os_minor_version = 12;</code>
   * @return The osMinorVersion.
   */
  java.lang.String getOsMinorVersion();
  /**
   * <code>string os_minor_version = 12;</code>
   * @return The bytes for osMinorVersion.
   */
  com.google.protobuf.ByteString
      getOsMinorVersionBytes();

  /**
   * <code>string aliases = 13;</code>
   * @return The aliases.
   */
  java.lang.String getAliases();
  /**
   * <code>string aliases = 13;</code>
   * @return The bytes for aliases.
   */
  com.google.protobuf.ByteString
      getAliasesBytes();

  /**
   * <code>string addresses = 14;</code>
   * @return The addresses.
   */
  java.lang.String getAddresses();
  /**
   * <code>string addresses = 14;</code>
   * @return The bytes for addresses.
   */
  com.google.protobuf.ByteString
      getAddressesBytes();

  /**
   * <code>repeated .agent.AgentCommand commands = 15;</code>
   */
  java.util.List<com.park.utmstack.service.grpc.AgentCommand> 
      getCommandsList();
  /**
   * <code>repeated .agent.AgentCommand commands = 15;</code>
   */
  com.park.utmstack.service.grpc.AgentCommand getCommands(int index);
  /**
   * <code>repeated .agent.AgentCommand commands = 15;</code>
   */
  int getCommandsCount();
  /**
   * <code>repeated .agent.AgentCommand commands = 15;</code>
   */
  java.util.List<? extends com.park.utmstack.service.grpc.AgentCommandOrBuilder> 
      getCommandsOrBuilderList();
  /**
   * <code>repeated .agent.AgentCommand commands = 15;</code>
   */
  com.park.utmstack.service.grpc.AgentCommandOrBuilder getCommandsOrBuilder(
      int index);
}
