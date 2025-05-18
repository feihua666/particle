package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业行政处罚远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_punishment")
public interface DataCompanyPunishmentRpcFeignClient {









}
