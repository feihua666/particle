package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业经营异常远程调用
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_abnormal")
public interface DataCompanyAbnormalRpcFeignClient {









}
