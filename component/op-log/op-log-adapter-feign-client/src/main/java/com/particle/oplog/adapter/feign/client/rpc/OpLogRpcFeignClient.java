package com.particle.oplog.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 操作日志远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@FeignClient(name = "${particle.feign-client.name.op-log:op-log}",path = "/rpc/op_log")
public interface OpLogRpcFeignClient {









}
