package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权专利引证信息远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyIprPatentQuoteRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_ipr_patent_quote")
public interface DataCompanyIprPatentQuoteRpcFeignClient {









}
