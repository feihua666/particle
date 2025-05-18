package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业法院公告远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_court_announcement")
public interface DataCompanyCourtAnnouncementRpcFeignClient {









}
