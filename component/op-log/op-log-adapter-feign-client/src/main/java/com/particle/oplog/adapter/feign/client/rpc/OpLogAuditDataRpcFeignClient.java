package com.particle.oplog.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 操作日志审计数据远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@FeignClient(name = "${particle.feign-client.name.op-log:op-log}",path = "/rpc/op_log_audit_data")
public interface OpLogAuditDataRpcFeignClient {









}
