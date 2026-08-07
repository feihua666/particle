package com.particle.agi.adapter.feign.client.model.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * AI模型远程调用
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@FeignClient(name = "${particle.feign-client.name.agi:agi}",path = "/rpc/agi_ai_model")
public interface AgiAiModelRpcFeignClient {









}
