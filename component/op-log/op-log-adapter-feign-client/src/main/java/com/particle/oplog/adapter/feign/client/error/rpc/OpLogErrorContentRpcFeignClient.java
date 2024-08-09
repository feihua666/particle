package com.particle.oplog.adapter.feign.client.error.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 操作异常日志内容远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@FeignClient(name = "${particle.feign-client.name.op-log:op-log}",path = "/rpc/op_log_error_content")
public interface OpLogErrorContentRpcFeignClient {









}
