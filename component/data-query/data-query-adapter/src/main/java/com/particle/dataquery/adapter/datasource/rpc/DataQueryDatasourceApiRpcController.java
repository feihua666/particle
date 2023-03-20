package com.particle.dataquery.adapter.datasource.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApiApplicationService;
import com.particle.dataquery.adapter.feign.client.datasource.rpc.DataQueryDatasourceApiRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据源接口远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Api(tags = "数据查询数据源接口远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_query_datasource_api")
public class DataQueryDatasourceApiRpcController extends AbstractBaseRpcAdapter implements DataQueryDatasourceApiRpcFeignClient  {

	@Autowired
	private IDataQueryDatasourceApiApplicationService iDataQueryDatasourceApiApplicationService;


}