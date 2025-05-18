package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业失信被执行人远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_discredited_judgment_debtor")
public interface DataCompanyDiscreditedJudgmentDebtorRpcFeignClient {









}
