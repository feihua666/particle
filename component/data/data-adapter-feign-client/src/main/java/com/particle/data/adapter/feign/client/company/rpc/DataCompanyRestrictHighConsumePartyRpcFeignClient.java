package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业限制高消费当事人远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_restrict_high_consume_party")
public interface DataCompanyRestrictHighConsumePartyRpcFeignClient {









}
