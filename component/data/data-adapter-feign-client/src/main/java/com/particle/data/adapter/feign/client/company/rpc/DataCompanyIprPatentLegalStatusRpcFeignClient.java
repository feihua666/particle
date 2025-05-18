package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权专利法律状态远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_ipr_patent_legal_status")
public interface DataCompanyIprPatentLegalStatusRpcFeignClient {









}
