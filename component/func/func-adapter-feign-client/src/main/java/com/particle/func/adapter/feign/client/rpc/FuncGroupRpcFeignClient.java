package com.particle.func.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 功能组远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.funcGroup:funcGroup}",path = "/rpc")
public interface FuncGroupRpcFeignClient {









}
