package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业立案信息当事人远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_case_filing_party")
public interface DataCompanyCaseFilingPartyRpcFeignClient {









}
