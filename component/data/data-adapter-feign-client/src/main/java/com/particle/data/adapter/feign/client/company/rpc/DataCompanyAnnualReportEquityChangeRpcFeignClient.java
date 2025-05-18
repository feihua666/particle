package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业年报股权变更远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_annual_report_equity_change")
public interface DataCompanyAnnualReportEquityChangeRpcFeignClient {









}
