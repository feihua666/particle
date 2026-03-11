package com.particle.dataquery.adapter.feign.client.dataapi.rpc;

import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import com.particle.global.dto.response.RawResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 数据查询数据接口远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@FeignClient(name = "${particle.feign-client.dataquery.name:dataquery-start}", contextId = "dataQueryDataApiRpcFeignClient", url = "${particle.feign-client.dataquery.url:}", path = "/rpc/data_query_data_api")
public interface DataQueryDataApiRpcFeignClient {

	/**
	 *
	 * @param dataQueryDataApiQueryCommand
	 * @return
	 */
	@PostMapping("/invoke")
    RawResponse invoke(@RequestBody DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand);

}
