package com.particle.dataquery.adapter.dataapi.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataquery.client.dataapi.api.IDataQueryDataApiApplicationService;
import com.particle.dataquery.adapter.feign.client.dataapi.rpc.DataQueryDataApiRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据接口远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Api(tags = "数据查询数据接口远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_query_data_api")
public class DataQueryDataApiRpcController extends AbstractBaseRpcAdapter implements DataQueryDataApiRpcFeignClient  {

	@Autowired
	private IDataQueryDataApiApplicationService iDataQueryDataApiApplicationService;


}