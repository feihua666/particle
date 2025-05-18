package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业裁判文书内容远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_judgment_document_content")
public interface DataCompanyJudgmentDocumentContentRpcFeignClient {









}
