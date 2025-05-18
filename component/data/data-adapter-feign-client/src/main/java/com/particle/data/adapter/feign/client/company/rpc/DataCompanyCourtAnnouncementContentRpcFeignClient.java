package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业法院公告内容远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_court_announcement_content")
public interface DataCompanyCourtAnnouncementContentRpcFeignClient {









}
