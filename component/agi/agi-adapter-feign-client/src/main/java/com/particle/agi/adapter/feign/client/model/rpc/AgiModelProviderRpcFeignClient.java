package com.particle.agi.adapter.feign.client.model.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * AI模型提供商远程调用
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@FeignClient(name = "${particle.feign-client.name.agi:agi}",path = "/rpc/agi_model_provider")
public interface AgiModelProviderRpcFeignClient {









}
