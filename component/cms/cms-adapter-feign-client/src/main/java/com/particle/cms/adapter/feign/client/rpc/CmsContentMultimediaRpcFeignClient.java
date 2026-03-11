package com.particle.cms.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 内容多媒体远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@FeignClient(name = "${particle.feign-client.cms.name:cms-start}", contextId = "cmsContentMultimediaRpcFeignClient", url = "${particle.feign-client.cms.url:}", path = "/rpc/cms_content_multimedia")
public interface CmsContentMultimediaRpcFeignClient {









}
