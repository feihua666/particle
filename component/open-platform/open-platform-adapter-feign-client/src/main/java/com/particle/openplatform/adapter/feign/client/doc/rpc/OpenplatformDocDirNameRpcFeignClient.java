package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口目录名称远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformDocDirNameRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_doc_dir_name")
public interface OpenplatformDocDirNameRpcFeignClient {









}
