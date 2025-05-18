package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业年报对外担保远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_annual_report_foreign_guarantee")
public interface DataCompanyAnnualReportForeignGuaranteeRpcFeignClient {









}
