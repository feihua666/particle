package com.particle.openplatform.adapter.feign.client.app.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台应用远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_app")
public interface OpenplatformAppRpcFeignClient {









}
