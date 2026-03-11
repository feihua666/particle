package com.particle.cms.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 内容分类远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@FeignClient(name = "${particle.feign-client.cms.name:cms-start}", contextId = "cmsContentCategoryRpcFeignClient", url = "${particle.feign-client.cms.url:}", path = "/rpc/cms_content_category")
public interface CmsContentCategoryRpcFeignClient {









}
