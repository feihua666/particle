package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业融资产品竞品关系远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyVcProductCompetitiveProductRelRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_vc_product_competitive_product_rel")
public interface DataCompanyVcProductCompetitiveProductRelRpcFeignClient {









}
