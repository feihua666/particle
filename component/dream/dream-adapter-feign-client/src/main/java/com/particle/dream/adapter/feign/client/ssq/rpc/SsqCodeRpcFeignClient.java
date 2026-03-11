package com.particle.dream.adapter.feign.client.ssq.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 双色球号码远程调用
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@FeignClient(name = "${particle.feign-client.dream.name:dream-start}", contextId = "ssqCodeRpcFeignClient", url = "${particle.feign-client.dream.url:}", path = "/rpc/ssq_code")
public interface SsqCodeRpcFeignClient {









}
