package com.particle.dataquery.adapter.datasource.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceApiRepresentationApplicationService;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 数据查询数据源接口测试后台管理pc或平板端前端适配器
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Tag(name = "数据查询数据源接口测试pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_query_datasource_api/test")
public class DataQueryDatasourceApiAdminWebTestController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDatasourceApiRepresentationApplicationService iDataQueryDatasourceApiRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:test')")
	@Operation(summary = "测试数据查询数据源接口")
	@PostMapping("/api_test")
	public Object test(@RequestBody DataQueryDatasourceApiQueryCommand dataQueryDatasourceApiQueryCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.test(dataQueryDatasourceApiQueryCommand);
	}
}