package com.particle.openplatform.adapter.feign.client.providerrecord.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台开放接口供应商调用记录参数远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_provider_record_param")
public interface OpenplatformProviderRecordParamRpcFeignClient {









}
