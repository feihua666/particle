package com.particle.openplatform.adapter.feign.client.provider.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台供应商接口远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_provider_api")
public interface OpenplatformProviderApiRpcFeignClient {









}
