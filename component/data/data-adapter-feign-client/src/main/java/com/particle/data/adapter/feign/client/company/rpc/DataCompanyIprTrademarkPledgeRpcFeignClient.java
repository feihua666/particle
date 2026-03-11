package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权商标质押信息远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyIprTrademarkPledgeRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_ipr_trademark_pledge")
public interface DataCompanyIprTrademarkPledgeRpcFeignClient {









}
