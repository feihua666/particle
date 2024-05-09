package com.particle.crm.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 客户公司部门远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@FeignClient(name = "${particle.feign-client.name.crm:crm}",path = "/rpc/crm_dept")
public interface CrmDeptRpcFeignClient {









}
