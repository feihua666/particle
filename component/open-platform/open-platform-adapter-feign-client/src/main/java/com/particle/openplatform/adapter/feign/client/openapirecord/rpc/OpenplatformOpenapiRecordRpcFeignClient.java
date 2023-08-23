package com.particle.openplatform.adapter.feign.client.openapirecord.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台开放接口调用记录远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_openapi_record")
public interface OpenplatformOpenapiRecordRpcFeignClient {









}
