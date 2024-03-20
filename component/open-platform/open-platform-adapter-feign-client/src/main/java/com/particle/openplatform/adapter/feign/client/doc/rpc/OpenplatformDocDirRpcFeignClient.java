package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口目录远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_doc_dir")
public interface OpenplatformDocDirRpcFeignClient {









}
