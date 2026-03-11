package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权专利同族信息远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyIprPatentFamilyRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_ipr_patent_family")
public interface DataCompanyIprPatentFamilyRpcFeignClient {









}
