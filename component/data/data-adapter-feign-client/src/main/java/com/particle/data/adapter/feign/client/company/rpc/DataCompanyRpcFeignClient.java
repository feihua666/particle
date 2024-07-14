package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company")
public interface DataCompanyRpcFeignClient {









}
