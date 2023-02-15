package com.particle.lowcode.adapter.feign.client.generator.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 低代码生成远程调用
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@FeignClient(name = "${particle.feign-client.name.low-code:low-code}",path = "/rpc/lowcode-segment-gen")
public interface LowcodeSegmentGenRpcFeignClient {









}
