package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业md5远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyMd5RpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_md5")
public interface DataCompanyMd5RpcFeignClient {









}
