package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业年报变更远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyAnnualReportChangeRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_annual_report_change")
public interface DataCompanyAnnualReportChangeRpcFeignClient {









}
