package com.particle.test.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 测试远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@FeignClient(name = "${particle.feign-client.name.test:test}",path = "/rpc/test")
public interface TestRpcFeignClient {









}
