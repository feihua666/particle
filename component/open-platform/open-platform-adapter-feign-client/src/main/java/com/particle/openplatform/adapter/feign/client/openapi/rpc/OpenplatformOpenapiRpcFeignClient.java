package com.particle.openplatform.adapter.feign.client.openapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台开放接口远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_openapi")
public interface OpenplatformOpenapiRpcFeignClient {









}
