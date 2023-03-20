package com.particle.dataquery.adapter.datasource.web.admin;

import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApiApplicationService;
import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceApiRepresentationApplicationService;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiCreateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiUpdateCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiPageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 数据查询数据源接口后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Api(tags = "数据查询数据源接口pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_query_datasource_api")
public class DataQueryDatasourceApiAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDatasourceApiApplicationService iDataQueryDatasourceApiApplicationService;
	@Autowired
	private IDataQueryDatasourceApiRepresentationApplicationService iDataQueryDatasourceApiRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:create')")
	@ApiOperation("添加数据查询数据源接口")
	@PostMapping("/create")
	public SingleResponse<DataQueryDatasourceApiVO> create(@RequestBody DataQueryDatasourceApiCreateCommand dataQueryDatasourceApiCreateCommand){
		return iDataQueryDatasourceApiApplicationService.create(dataQueryDatasourceApiCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:delete')")
	@ApiOperation("删除数据查询数据源接口")
	@DeleteMapping("/delete")
	public SingleResponse<DataQueryDatasourceApiVO> delete(@RequestBody IdCommand deleteCommand){
		return iDataQueryDatasourceApiApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:update')")
	@ApiOperation("更新数据查询数据源接口")
	@PutMapping("/update")
	public SingleResponse<DataQueryDatasourceApiVO> update(@RequestBody DataQueryDatasourceApiUpdateCommand dataQueryDatasourceApiUpdateCommand){
		return iDataQueryDatasourceApiApplicationService.update(dataQueryDatasourceApiUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:update')")
	@ApiOperation("数据查询数据源接口更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DataQueryDatasourceApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:detail')")
	@ApiOperation("数据查询数据源接口详情展示")
	@GetMapping("/detail")
	public SingleResponse<DataQueryDatasourceApiVO> queryDetail(IdCommand detailCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:queryList')")
	@ApiOperation("列表查询数据查询数据源接口")
	@GetMapping("/list")
	public MultiResponse<DataQueryDatasourceApiVO> queryList(DataQueryDatasourceApiQueryListCommand dataQueryDatasourceApiQueryListCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.queryList(dataQueryDatasourceApiQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:pageQuery')")
	@ApiOperation("分页查询数据查询数据源接口")
	@GetMapping("/page")
	public PageResponse<DataQueryDatasourceApiVO> pageQueryList(DataQueryDatasourceApiPageQueryCommand dataQueryDatasourceApiPageQueryCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.pageQuery(dataQueryDatasourceApiPageQueryCommand);
	}

}