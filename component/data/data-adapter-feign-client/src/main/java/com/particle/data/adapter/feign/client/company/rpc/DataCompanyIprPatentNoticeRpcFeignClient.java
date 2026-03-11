package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权专利通知书信息远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyIprPatentNoticeRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_ipr_patent_notice")
public interface DataCompanyIprPatentNoticeRpcFeignClient {









}
