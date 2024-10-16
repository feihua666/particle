package com.particle.openplatform.adapter.feign.client.app.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台应用额度远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_app_quota")
public interface OpenplatformAppQuotaRpcFeignClient {









}
