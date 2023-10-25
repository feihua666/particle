package com.particle.usagecount.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 使用次数配置远程调用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@FeignClient(name = "${particle.feign-client.name.usage-count:usage-count}",path = "/rpc/usage_count_config")
public interface UsageCountConfigRpcFeignClient {









}
