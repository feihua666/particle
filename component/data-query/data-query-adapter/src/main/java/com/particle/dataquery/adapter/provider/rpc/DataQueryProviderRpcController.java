package com.particle.dataquery.adapter.provider.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataquery.adapter.feign.client.provider.rpc.DataQueryProviderRpcFeignClient;
import com.particle.dataquery.adapter.feign.client.provider.rpc.DataQueryProviderTransRpcFeignClient;
import com.particle.dataquery.client.provider.api.IDataQueryProviderApplicationService;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 数据查询供应商远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Tag(name = "数据查询供应商远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_query_provider")
public class DataQueryProviderRpcController extends AbstractBaseRpcAdapter implements DataQueryProviderRpcFeignClient, DataQueryProviderTransRpcFeignClient {

	@Autowired
	private IDataQueryProviderApplicationService iDataQueryProviderApplicationService;

	@Autowired
	private DataQueryProviderTransServiceImpl dataQueryProviderTransService;


	@Override
	public boolean supportBatch(String type) {
		return dataQueryProviderTransService.supportBatch(type);
	}


	@Override
	public List<TransResult<DataQueryProviderTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return dataQueryProviderTransService.transBatch(type, keys);
	}
}