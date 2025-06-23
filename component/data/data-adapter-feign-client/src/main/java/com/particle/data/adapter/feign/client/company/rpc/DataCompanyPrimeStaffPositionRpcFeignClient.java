package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业主要人员职位远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_prime_staff_position")
public interface DataCompanyPrimeStaffPositionRpcFeignClient {









}
