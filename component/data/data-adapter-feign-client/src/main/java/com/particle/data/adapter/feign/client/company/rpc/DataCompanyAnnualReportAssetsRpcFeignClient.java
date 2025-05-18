package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业资产状况信息远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_annual_report_assets")
public interface DataCompanyAnnualReportAssetsRpcFeignClient {









}
