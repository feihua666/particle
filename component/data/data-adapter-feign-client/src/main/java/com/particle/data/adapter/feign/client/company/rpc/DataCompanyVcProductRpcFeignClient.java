package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业融资产品远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyVcProductRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_vc_product")
public interface DataCompanyVcProductRpcFeignClient {









}
