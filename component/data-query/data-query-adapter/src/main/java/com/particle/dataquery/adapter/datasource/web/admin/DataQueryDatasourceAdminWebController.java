package com.particle.dataquery.adapter.datasource.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApplicationService;
import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceRepresentationApplicationService;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceCreateCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceUpdateCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourcePageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 数据查询数据源后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Tag(name = "数据查询数据源pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_query_datasource")
public class DataQueryDatasourceAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDatasourceApplicationService iDataQueryDatasourceApplicationService;
	@Autowired
	private IDataQueryDatasourceRepresentationApplicationService iDataQueryDatasourceRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:create')")
	@Operation(summary = "添加数据查询数据源")
	@PostMapping("/create")
	@OpLog(name = "添加数据查询数据源",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.create)
	public SingleResponse<DataQueryDatasourceVO> create(@RequestBody DataQueryDatasourceCreateCommand dataQueryDatasourceCreateCommand){
		return iDataQueryDatasourceApplicationService.create(dataQueryDatasourceCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:delete')")
	@Operation(summary = "删除数据查询数据源")
	@DeleteMapping("/delete")
	@OpLog(name = "删除数据查询数据源",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.delete)
	public SingleResponse<DataQueryDatasourceVO> delete(@RequestBody IdCommand deleteCommand){
		return iDataQueryDatasourceApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:update')")
	@Operation(summary = "更新数据查询数据源")
	@PutMapping("/update")
	@OpLog(name = "更新数据查询数据源",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.update)
	public SingleResponse<DataQueryDatasourceVO> update(@RequestBody DataQueryDatasourceUpdateCommand dataQueryDatasourceUpdateCommand){
		return iDataQueryDatasourceApplicationService.update(dataQueryDatasourceUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:update')")
	@Operation(summary = "数据查询数据源更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DataQueryDatasourceVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDataQueryDatasourceRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:detail')")
	@Operation(summary = "数据查询数据源详情展示")
	@GetMapping("/detail")
	public SingleResponse<DataQueryDatasourceVO> queryDetail(IdCommand detailCommand){
		return iDataQueryDatasourceRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:queryList')")
	@Operation(summary = "列表查询数据查询数据源")
	@GetMapping("/list")
	public MultiResponse<DataQueryDatasourceVO> queryList(DataQueryDatasourceQueryListCommand dataQueryDatasourceQueryListCommand){
		return iDataQueryDatasourceRepresentationApplicationService.queryList(dataQueryDatasourceQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasource:pageQuery')")
	@Operation(summary = "分页查询数据查询数据源")
	@GetMapping("/page")
	public PageResponse<DataQueryDatasourceVO> pageQueryList(DataQueryDatasourcePageQueryCommand dataQueryDatasourcePageQueryCommand){
		return iDataQueryDatasourceRepresentationApplicationService.pageQuery(dataQueryDatasourcePageQueryCommand);
	}

}
