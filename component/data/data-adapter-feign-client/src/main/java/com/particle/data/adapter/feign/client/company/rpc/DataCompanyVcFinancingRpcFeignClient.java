package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业融资远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyVcFinancingRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_vc_financing")
public interface DataCompanyVcFinancingRpcFeignClient {









}
