package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业股权出质远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyEquityPledgeRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_equity_pledge")
public interface DataCompanyEquityPledgeRpcFeignClient {









}
