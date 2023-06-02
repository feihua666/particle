package com.particle.dataquery.adapter.dataapi.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataquery.client.dataapi.api.IDataQueryDataApiApplicationService;
import com.particle.dataquery.adapter.feign.client.dataapi.rpc.DataQueryDataApiRpcFeignClient;
import com.particle.dataquery.client.dataapi.api.representation.IDataQueryDataApiRepresentationApplicationService;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	@Autowired
	private IDataQueryDataApiRepresentationApplicationService iDataQueryDataApiRepresentationApplicationService;


	@PostMapping("/invoke")
	@Override
	public Object invoke(String code, Object command, String queryString) {

		DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand = new DataQueryDataApiQueryCommand();
		dataQueryDataApiQueryCommand.setUrl(code);
		dataQueryDataApiQueryCommand.setParam(command);
		dataQueryDataApiQueryCommand.setQueryString(queryString);

		return iDataQueryDataApiRepresentationApplicationService.dataApiQuery(dataQueryDataApiQueryCommand);
	}
}