package com.particle.openplatform.adapter.feign.client.provider.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台开放接口供应商远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_provider")
public interface OpenplatformProviderRpcFeignClient {









}
