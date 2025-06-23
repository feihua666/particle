package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权出质远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_ipr_pledge")
public interface DataCompanyIprPledgeRpcFeignClient {









}
