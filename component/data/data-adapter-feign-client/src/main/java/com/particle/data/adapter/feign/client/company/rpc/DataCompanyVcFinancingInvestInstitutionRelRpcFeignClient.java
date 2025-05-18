package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业融资历史投资机构关系远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_vc_financing_invest_institution_rel")
public interface DataCompanyVcFinancingInvestInstitutionRelRpcFeignClient {









}
