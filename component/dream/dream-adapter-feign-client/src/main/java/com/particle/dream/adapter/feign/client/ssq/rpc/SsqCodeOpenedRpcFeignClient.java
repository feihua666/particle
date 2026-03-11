package com.particle.dream.adapter.feign.client.ssq.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 双色球开奖远程调用
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@FeignClient(name = "${particle.feign-client.dream.name:dream-start}", contextId = "ssqCodeOpenedRpcFeignClient", url = "${particle.feign-client.dream.url:}", path = "/rpc/ssq_code_opened")
public interface SsqCodeOpenedRpcFeignClient {









}
