package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口文档接口远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_doc_api")
public interface OpenplatformDocApiRpcFeignClient {









}
