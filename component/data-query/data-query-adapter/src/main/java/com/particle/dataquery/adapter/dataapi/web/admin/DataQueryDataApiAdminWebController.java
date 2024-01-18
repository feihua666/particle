package com.particle.dataquery.adapter.dataapi.web.admin;

import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dataquery.client.dataapi.api.IDataQueryDataApiApplicationService;
import com.particle.dataquery.client.dataapi.api.representation.IDataQueryDataApiRepresentationApplicationService;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiCreateCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiUpdateCommand;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiPageQueryCommand;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryListCommand;
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
 * 数据查询数据接口后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Tag(name = "数据查询数据接口pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_query_data_api")
public class DataQueryDataApiAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDataApiApplicationService iDataQueryDataApiApplicationService;
	@Autowired
	private IDataQueryDataApiRepresentationApplicationService iDataQueryDataApiRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:create')")
	@Operation(summary = "添加数据查询数据接口")
	@PostMapping("/create")
	@OpLog(name = "添加数据查询数据接口",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.create)
	public SingleResponse<DataQueryDataApiVO> create(@RequestBody DataQueryDataApiCreateCommand dataQueryDataApiCreateCommand){
		return iDataQueryDataApiApplicationService.create(dataQueryDataApiCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:delete')")
	@Operation(summary = "删除数据查询数据接口")
	@DeleteMapping("/delete")
	@OpLog(name = "删除数据查询数据接口",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.delete)
	public SingleResponse<DataQueryDataApiVO> delete(@RequestBody IdCommand deleteCommand){
		return iDataQueryDataApiApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:update')")
	@Operation(summary = "更新数据查询数据接口")
	@PutMapping("/update")
	@OpLog(name = "更新数据查询数据接口",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.update)
	public SingleResponse<DataQueryDataApiVO> update(@RequestBody DataQueryDataApiUpdateCommand dataQueryDataApiUpdateCommand){
		return iDataQueryDataApiApplicationService.update(dataQueryDataApiUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:update')")
	@Operation(summary = "数据查询数据接口更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DataQueryDataApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDataQueryDataApiRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:detail')")
	@Operation(summary = "数据查询数据接口详情展示")
	@GetMapping("/detail")
	public SingleResponse<DataQueryDataApiVO> queryDetail(IdCommand detailCommand){
		return iDataQueryDataApiRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:queryList')")
	@Operation(summary = "列表查询数据查询数据接口")
	@GetMapping("/list")
	public MultiResponse<DataQueryDataApiVO> queryList(DataQueryDataApiQueryListCommand dataQueryDataApiQueryListCommand){
		return iDataQueryDataApiRepresentationApplicationService.queryList(dataQueryDataApiQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:pageQuery')")
	@Operation(summary = "分页查询数据查询数据接口")
	@GetMapping("/page")
	public PageResponse<DataQueryDataApiVO> pageQueryList(DataQueryDataApiPageQueryCommand dataQueryDataApiPageQueryCommand){
		return iDataQueryDataApiRepresentationApplicationService.pageQuery(dataQueryDataApiPageQueryCommand);
	}


	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:copy')")
	@Operation(summary = "数据查询数据接口复制")
	@PostMapping("/copy")
	@OpLog(name = "数据查询数据接口复制",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.create)
	public SingleResponse<DataQueryDataApiVO> copy(@RequestBody IdCommand copyCommand){
		return iDataQueryDataApiApplicationService.copy(copyCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:deleteCache')")
	@Operation(summary = "删除数据查询数据接口缓存")
	@DeleteMapping("/deleteCache")
	@OpLog(name = "删除数据查询数据接口缓存",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.delete)
	public SingleResponse<String> deleteCache(@RequestBody IdCommand deleteCommand){
		return iDataQueryDataApiApplicationService.deleteCache(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryDataApi:refreshCache')")
	@Operation(summary = "刷新数据查询数据接口缓存")
	@PutMapping("/refreshCache")
	@OpLog(name = "刷新数据查询数据接口缓存",module = OpLogConstants.Module.dataQuery,type = OpLogConstants.Type.update)
	public SingleResponse<String> refreshCache(@RequestBody IdCommand deleteCommand){
		return iDataQueryDataApiApplicationService.refreshCache(deleteCommand);
	}
}