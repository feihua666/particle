package com.particle.dataquery.adapter.feign.client.dataapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 数据查询数据接口远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@FeignClient(name = "${particle.feign-client.name.data-query:data-query}",path = "/rpc/data_query_data_api")
public interface DataQueryDataApiRpcFeignClient {

	/**
	 *
	 * @param code 一般是一个类似于url的字符串 如：/jc/quality_analyse
	 * @param command
	 * @param queryString
	 * @return
	 */
	@PostMapping("/invoke")
	Object invoke(String code, Object command, String queryString);

}
