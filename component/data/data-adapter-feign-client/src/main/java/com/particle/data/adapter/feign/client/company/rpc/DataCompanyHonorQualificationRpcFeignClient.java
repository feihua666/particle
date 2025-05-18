package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业荣誉资质远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_honor_qualification")
public interface DataCompanyHonorQualificationRpcFeignClient {









}
