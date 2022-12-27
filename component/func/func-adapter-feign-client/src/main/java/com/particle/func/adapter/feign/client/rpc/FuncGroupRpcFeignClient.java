package com.particle.func.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 功能组远程调用
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@FeignClient(name = "${particle.feign-client.name.func:func}",path = "/rpc/func-group")
public interface FuncGroupRpcFeignClient {









}
