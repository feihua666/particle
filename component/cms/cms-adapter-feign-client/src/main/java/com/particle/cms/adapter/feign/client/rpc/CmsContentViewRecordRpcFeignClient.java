package com.particle.cms.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 内容访问记录远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@FeignClient(name = "${particle.feign-client.name.cms:cms}",path = "/rpc/cms_content_view_record")
public interface CmsContentViewRecordRpcFeignClient {









}
