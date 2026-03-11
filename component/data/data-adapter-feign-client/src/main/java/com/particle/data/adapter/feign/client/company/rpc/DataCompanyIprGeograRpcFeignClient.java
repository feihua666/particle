package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权地理标识远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyIprGeograRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_ipr_geogra")
public interface DataCompanyIprGeograRpcFeignClient {









}
