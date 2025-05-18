package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业年报行政许可远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_annual_report_administrative_license")
public interface DataCompanyAnnualReportAdministrativeLicenseRpcFeignClient {









}
