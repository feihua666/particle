package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业年报远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyAnnualReportRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_annual_report")
public interface DataCompanyAnnualReportRpcFeignClient {









}
