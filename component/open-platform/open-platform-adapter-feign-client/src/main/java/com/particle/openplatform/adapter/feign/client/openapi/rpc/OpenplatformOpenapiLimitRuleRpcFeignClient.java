package com.particle.openplatform.adapter.feign.client.openapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台开放接口限制规则远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_openapi_limit_rule")
public interface OpenplatformOpenapiLimitRuleRpcFeignClient {









}
