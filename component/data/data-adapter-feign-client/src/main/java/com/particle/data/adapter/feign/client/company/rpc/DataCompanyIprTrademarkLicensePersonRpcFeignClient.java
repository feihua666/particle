package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权商标许可人远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_ipr_trademark_license_person")
public interface DataCompanyIprTrademarkLicensePersonRpcFeignClient {









}
