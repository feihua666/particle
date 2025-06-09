package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业统计远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_statistic")
public interface DataCompanyStatisticRpcFeignClient {









}
