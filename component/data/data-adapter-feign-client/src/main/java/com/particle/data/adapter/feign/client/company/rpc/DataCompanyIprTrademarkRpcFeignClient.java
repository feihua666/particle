package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权商标远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyIprTrademarkRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_ipr_trademark")
public interface DataCompanyIprTrademarkRpcFeignClient {









}
