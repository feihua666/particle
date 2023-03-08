package com.particle.dataquery.adapter.datasource.web.admin;

import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApplicationService;
import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceRepresentationApplicationService;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceCreateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceUpdateCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourcePageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceQueryListCommand;
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
 * 数据查询数据源后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Api(tags = "数据查询数据源pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_query_datasource")
public class DataQueryDatasourceAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDatasourceApplicationService iDataQueryDatasourceApplicationService;
	@Autowired
	private IDataQueryDatasourceRepresentationApplicationService iDataQueryDatasourceRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:create')")
	@ApiOperation("添加数据查询数据源")
	@PostMapping("/create")
	public SingleResponse<DataQueryDatasourceVO> create(@RequestBody DataQueryDatasourceCreateCommand dataQueryDatasourceCreateCommand){
		return iDataQueryDatasourceApplicationService.create(dataQueryDatasourceCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:delete')")
	@ApiOperation("删除数据查询数据源")
	@DeleteMapping("/delete")
	public SingleResponse<DataQueryDatasourceVO> delete(@RequestBody IdCommand deleteCommand){
		return iDataQueryDatasourceApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:update')")
	@ApiOperation("更新数据查询数据源")
	@PutMapping("/update")
	public SingleResponse<DataQueryDatasourceVO> update(@RequestBody DataQueryDatasourceUpdateCommand dataQueryDatasourceUpdateCommand){
		return iDataQueryDatasourceApplicationService.update(dataQueryDatasourceUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:update')")
	@ApiOperation("数据查询数据源更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DataQueryDatasourceVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDataQueryDatasourceRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:detail')")
	@ApiOperation("数据查询数据源详情展示")
	@GetMapping("/detail")
	public SingleResponse<DataQueryDatasourceVO> queryDetail(IdCommand detailCommand){
		return iDataQueryDatasourceRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:queryList')")
	@ApiOperation("列表查询数据查询数据源")
	@GetMapping("/list")
	public MultiResponse<DataQueryDatasourceVO> queryList(DataQueryDatasourceQueryListCommand dataQueryDatasourceQueryListCommand){
		return iDataQueryDatasourceRepresentationApplicationService.queryList(dataQueryDatasourceQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:pageQuery')")
	@ApiOperation("分页查询数据查询数据源")
	@GetMapping("/page")
	public PageResponse<DataQueryDatasourceVO> pageQueryList(DataQueryDatasourcePageQueryCommand dataQueryDatasourcePageQueryCommand){
		return iDataQueryDatasourceRepresentationApplicationService.pageQuery(dataQueryDatasourcePageQueryCommand);
	}

}