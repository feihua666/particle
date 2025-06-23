package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业终本案件远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_end_case")
public interface DataCompanyEndCaseRpcFeignClient {









}
