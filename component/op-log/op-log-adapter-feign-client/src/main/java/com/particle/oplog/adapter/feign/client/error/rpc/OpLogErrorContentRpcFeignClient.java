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
@FeignClient(name = "${particle.feign-client.oplog.name:oplog-start}", contextId = "opLogErrorContentRpcFeignClient", url = "${particle.feign-client.oplog.url:}", path = "/rpc/op_log_error_content")
public interface OpLogErrorContentRpcFeignClient {









}
