package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业开庭公告内容远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_open_court_announcement_content")
public interface DataCompanyOpenCourtAnnouncementContentRpcFeignClient {









}
