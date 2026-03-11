package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口文档接口与目录关系远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformDocApiDirRelRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_doc_api_dir_rel")
public interface OpenplatformDocApiDirRelRpcFeignClient {









}
