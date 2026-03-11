package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业开庭公告当事人远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyOpenCourtAnnouncementPartyRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_open_court_announcement_party")
public interface DataCompanyOpenCourtAnnouncementPartyRpcFeignClient {









}
