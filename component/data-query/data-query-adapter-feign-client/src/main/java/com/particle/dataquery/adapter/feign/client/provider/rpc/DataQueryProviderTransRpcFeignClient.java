package com.particle.dataquery.adapter.feign.client.provider.rpc;

import com.particle.dataquery.client.provider.dto.data.DataQueryProviderTransVO;
import com.particle.global.trans.api.ITransService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 数据查询供应商翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-17 10:10:43
 */
@FeignClient(name = "${particle.feign-client.name.data-query:data-query}",path = "/rpc/data_query_provider")
public interface DataQueryProviderTransRpcFeignClient extends ITransService<DataQueryProviderTransVO,Long> {
}
