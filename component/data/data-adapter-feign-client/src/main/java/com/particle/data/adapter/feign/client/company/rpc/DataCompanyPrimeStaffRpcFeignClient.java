package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业主要人员远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_prime_staff")
public interface DataCompanyPrimeStaffRpcFeignClient {









}
