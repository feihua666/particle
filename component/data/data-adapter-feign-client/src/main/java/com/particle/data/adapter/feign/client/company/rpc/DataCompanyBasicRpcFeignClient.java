package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业基本信息远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_basic")
public interface DataCompanyBasicRpcFeignClient {









}
