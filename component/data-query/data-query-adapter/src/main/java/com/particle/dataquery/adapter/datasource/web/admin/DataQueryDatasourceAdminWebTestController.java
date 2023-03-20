package com.particle.dataquery.adapter.datasource.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceApiRepresentationApplicationService;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiCreateCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiTestCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据查询数据源接口测试后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Api(tags = "数据查询数据源接口测试pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_query_datasource_api/test")
public class DataQueryDatasourceAdminWebTestController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDatasourceApiRepresentationApplicationService iDataQueryDatasourceApiRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:test')")
	@ApiOperation("测试数据查询数据源接口")
	@PostMapping("/api_test")
	public Object test(@RequestBody DataQueryDatasourceApiTestCommand dataQueryDatasourceApiTestCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.test(dataQueryDatasourceApiTestCommand);
	}
}