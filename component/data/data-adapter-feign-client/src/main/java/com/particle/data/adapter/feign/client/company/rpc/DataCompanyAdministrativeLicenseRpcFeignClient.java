package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业行政许可远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_administrative_license")
public interface DataCompanyAdministrativeLicenseRpcFeignClient {









}
