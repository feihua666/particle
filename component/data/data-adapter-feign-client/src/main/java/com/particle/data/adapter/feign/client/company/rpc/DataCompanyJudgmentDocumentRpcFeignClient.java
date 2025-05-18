package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业裁判文书远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_judgment_document")
public interface DataCompanyJudgmentDocumentRpcFeignClient {









}
