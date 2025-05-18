package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业知识产权专利统计远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_ipr_patent_statistic")
public interface DataCompanyIprPatentStatisticRpcFeignClient {









}
