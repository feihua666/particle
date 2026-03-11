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
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformProviderRecordParamRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_provider_record_param")
public interface OpenplatformProviderRecordParamRpcFeignClient {









}
