package com.particle.dataquery.adapter.datasource.web.admin;

import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApiApplicationService;
import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceApiRepresentationApplicationService;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiCreateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiUpdateCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiPageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "数据查询数据源接口pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_query_datasource_api")
public class DataQueryDatasourceApiAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDatasourceApiApplicationService iDataQueryDatasourceApiApplicationService;
	@Autowired
	private IDataQueryDatasourceApiRepresentationApplicationService iDataQueryDatasourceApiRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:create')")
	@Operation(summary = "添加数据查询数据源接口")
	@PostMapping("/create")
	@OpLog(name = "添加数据查询数据源接口",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.create)
	public SingleResponse<DataQueryDatasourceApiVO> create(@RequestBody DataQueryDatasourceApiCreateCommand dataQueryDatasourceApiCreateCommand){
		return iDataQueryDatasourceApiApplicationService.create(dataQueryDatasourceApiCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:delete')")
	@Operation(summary = "删除数据查询数据源接口")
	@DeleteMapping("/delete")
	@OpLog(name = "删除数据查询数据源接口",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.delete)
	public SingleResponse<DataQueryDatasourceApiVO> delete(@RequestBody IdCommand deleteCommand){
		return iDataQueryDatasourceApiApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:update')")
	@Operation(summary = "更新数据查询数据源接口")
	@PutMapping("/update")
	@OpLog(name = "更新数据查询数据源接口",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.update)
	public SingleResponse<DataQueryDatasourceApiVO> update(@RequestBody DataQueryDatasourceApiUpdateCommand dataQueryDatasourceApiUpdateCommand){
		return iDataQueryDatasourceApiApplicationService.update(dataQueryDatasourceApiUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:update')")
	@Operation(summary = "数据查询数据源接口更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DataQueryDatasourceApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:detail')")
	@Operation(summary = "数据查询数据源接口详情展示")
	@GetMapping("/detail")
	public SingleResponse<DataQueryDatasourceApiVO> queryDetail(IdCommand detailCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:queryList')")
	@Operation(summary = "列表查询数据查询数据源接口")
	@GetMapping("/list")
	public MultiResponse<DataQueryDatasourceApiVO> queryList(DataQueryDatasourceApiQueryListCommand dataQueryDatasourceApiQueryListCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.queryList(dataQueryDatasourceApiQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:pageQuery')")
	@Operation(summary = "分页查询数据查询数据源接口")
	@GetMapping("/page")
	public PageResponse<DataQueryDatasourceApiVO> pageQueryList(DataQueryDatasourceApiPageQueryCommand dataQueryDatasourceApiPageQueryCommand){
		return iDataQueryDatasourceApiRepresentationApplicationService.pageQuery(dataQueryDatasourceApiPageQueryCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:copy')")
	@Operation(summary = "数据查询数据源接口复制")
	@PostMapping("/copy")
	@OpLog(name = "数据查询数据源接口复制",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.create)
	public SingleResponse<DataQueryDatasourceApiVO> copy(@RequestBody IdCommand idCommand){
		return iDataQueryDatasourceApiApplicationService.copy(idCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:copydev')")
	@Operation(summary = "数据查询数据源接口复制dev")
	@PostMapping("/copydev")
	@OpLog(name = "数据查询数据源接口复制dev",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.create)
	public SingleResponse<DataQueryDatasourceApiVO> copydev(@RequestBody IdCommand idCommand){
		return iDataQueryDatasourceApiApplicationService.copydev(idCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:deleteCache')")
	@Operation(summary = "删除数据查询数据源接口缓存")
	@DeleteMapping("/deleteCache")
	@OpLog(name = "删除数据查询数据源接口缓存",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.delete)
	public SingleResponse<String> deleteCache(@RequestBody IdCommand deleteCommand){
		return iDataQueryDatasourceApiApplicationService.deleteCache(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:refreshCache')")
	@Operation(summary = "刷新数据查询数据源接口缓存")
	@PutMapping("/refreshCache")
	@OpLog(name = "刷新数据查询数据源接口缓存",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.update)
	public SingleResponse<String> refreshCache(@RequestBody IdCommand deleteCommand){
		return iDataQueryDatasourceApiApplicationService.refreshCache(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDatasourceApi:devMergeToMaster')")
	@Operation(summary = "数据查询数据源接口dev合并到master")
	@PutMapping("/devMergeToMaster")
	@OpLog(name = "数据查询数据源接口dev合并到master",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.update)
	public SingleResponse<DataQueryDatasourceApiVO> devMergeToMaster(@RequestBody IdCommand deleteCommand){
		return iDataQueryDatasourceApiApplicationService.devMergeToMaster(deleteCommand);
	}
}