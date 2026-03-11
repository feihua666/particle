package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业抽查检查远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanySpotCheckRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_spot_check")
public interface DataCompanySpotCheckRpcFeignClient {









}
