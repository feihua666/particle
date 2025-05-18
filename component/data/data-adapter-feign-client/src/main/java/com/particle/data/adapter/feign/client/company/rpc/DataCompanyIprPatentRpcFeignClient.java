package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权专利远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_ipr_patent")
public interface DataCompanyIprPatentRpcFeignClient {









}
