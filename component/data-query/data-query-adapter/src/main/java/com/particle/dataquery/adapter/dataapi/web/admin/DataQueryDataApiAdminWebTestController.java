package com.particle.dataquery.adapter.dataapi.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dataquery.client.dataapi.api.representation.IDataQueryDataApiRepresentationApplicationService;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据接口测试后台管理pc或平板端前端适配器
 * </p>
 *
 * @author yw
 * @since 2023-03-21 23:20:03
 */
@Api(tags = "数据查询数据接口测试pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_query_data_api/test")
public class DataQueryDataApiAdminWebTestController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDataApiRepresentationApplicationService iDataQueryDataApiRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:test')")
	@ApiOperation("测试数据查询数据接口")
	@PostMapping("/api_test")
	public Object test(@RequestBody DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand){
		return iDataQueryDataApiRepresentationApplicationService.dataApiQueryTest(dataQueryDataApiQueryCommand);
	}
}