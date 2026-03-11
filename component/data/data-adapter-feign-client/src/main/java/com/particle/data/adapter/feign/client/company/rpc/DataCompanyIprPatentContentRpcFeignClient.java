package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权专利内容远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyIprPatentContentRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_ipr_patent_content")
public interface DataCompanyIprPatentContentRpcFeignClient {









}
