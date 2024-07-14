package com.particle.data.adapter.feign.client.temp.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业md5ids远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/data_company_md5_ids")
public interface DataCompanyMd5IdsRpcFeignClient {









}
