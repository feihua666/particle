package com.particle.dataquery.adapter.datasource.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApplicationService;
import com.particle.dataquery.adapter.feign.client.datasource.rpc.DataQueryDatasourceRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据源远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Api(tags = "数据查询数据源远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_query_datasource")
public class DataQueryDatasourceRpcController extends AbstractBaseRpcAdapter implements DataQueryDatasourceRpcFeignClient  {

	@Autowired
	private IDataQueryDatasourceApplicationService iDataQueryDatasourceApplicationService;


}