package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业严重违法远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanySeriousIllegalRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_serious_illegal")
public interface DataCompanySeriousIllegalRpcFeignClient {









}
