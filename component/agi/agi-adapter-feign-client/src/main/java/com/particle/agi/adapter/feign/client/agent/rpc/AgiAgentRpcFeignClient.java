package com.particle.agi.adapter.feign.client.agent.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 智能体远程调用
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@FeignClient(name = "${particle.feign-client.agi.name:agi-start}", contextId = "agiAgentRpcFeignClient", url = "${particle.feign-client.agi.url:}", path = "/rpc/agi_agent")
public interface AgiAgentRpcFeignClient {









}
