package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业裁判文书当事人远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyJudgmentDocumentPartyRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_judgment_document_party")
public interface DataCompanyJudgmentDocumentPartyRpcFeignClient {









}
