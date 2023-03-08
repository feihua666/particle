package com.particle.dataquery.adapter.provider.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataquery.client.provider.api.IDataQueryProviderApplicationService;
import com.particle.dataquery.adapter.feign.client.provider.rpc.DataQueryProviderRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询供应商远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Api(tags = "数据查询供应商远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_query_provider")
public class DataQueryProviderRpcController extends AbstractBaseRpcAdapter implements DataQueryProviderRpcFeignClient  {

	@Autowired
	private IDataQueryProviderApplicationService iDataQueryProviderApplicationService;


}