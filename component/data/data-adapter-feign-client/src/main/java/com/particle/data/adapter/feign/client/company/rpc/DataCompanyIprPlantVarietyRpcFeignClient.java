package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权植物新品种远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_ipr_plant_variety")
public interface DataCompanyIprPlantVarietyRpcFeignClient {









}
