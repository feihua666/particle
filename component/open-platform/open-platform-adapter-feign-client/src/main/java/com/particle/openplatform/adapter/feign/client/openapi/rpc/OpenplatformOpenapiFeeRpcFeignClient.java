package com.particle.openplatform.adapter.feign.client.openapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台开放接口费用远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_openapi_fee")
public interface OpenplatformOpenapiFeeRpcFeignClient {









}
