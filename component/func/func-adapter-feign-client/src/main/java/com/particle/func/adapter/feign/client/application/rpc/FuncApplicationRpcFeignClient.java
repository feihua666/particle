package com.particle.func.adapter.feign.client.application.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 功能应用远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@FeignClient(name = "${particle.feign-client.func.name:func-start}", contextId = "funcApplicationRpcFeignClient", url = "${particle.feign-client.func.url:}", path = "/rpc/func_application")
public interface FuncApplicationRpcFeignClient {









}
