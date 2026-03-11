package com.particle.openplatform.adapter.feign.client.app.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台应用与开放接口配置远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformAppOpenapiRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_app_openapi")
public interface OpenplatformAppOpenapiRpcFeignClient {









}
