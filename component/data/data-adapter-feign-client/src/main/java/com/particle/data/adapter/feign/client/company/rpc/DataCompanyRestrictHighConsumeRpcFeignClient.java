package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业限制高消费远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyRestrictHighConsumeRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_restrict_high_consume")
public interface DataCompanyRestrictHighConsumeRpcFeignClient {









}
