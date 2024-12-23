package com.particle.dataconstraint.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dataconstraint.client.api.IDataScopeApplicationService;
import com.particle.dataconstraint.client.api.representation.IDataScopeRepresentationApplicationService;
import com.particle.dataconstraint.client.dto.command.DataScopeCreateCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeUpdateCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopePageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
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
 * 数据范围后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Tag(name = "数据范围pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_scope")
public class DataScopeAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataScopeApplicationService iDataScopeApplicationService;
	@Autowired
	private IDataScopeRepresentationApplicationService iDataScopeRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dataScope:create')")
	@Operation(summary = "添加数据范围")
	@PostMapping("/create")
	@OpLog(name = "添加数据范围",module = OpLogConstants.Module.dataconstraint,type = OpLogConstants.Type.create)
	public SingleResponse<DataScopeVO> create(@RequestBody DataScopeCreateCommand dataScopeCreateCommand){
		return iDataScopeApplicationService.create(dataScopeCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScope:delete')")
	@Operation(summary = "删除数据范围")
	@DeleteMapping("/delete")
	@OpLog(name = "删除数据范围",module = OpLogConstants.Module.dataconstraint,type = OpLogConstants.Type.delete)
	public SingleResponse<DataScopeVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_data_constraint_data_scope, DataConstraintContext.Action.delete.name());
		return iDataScopeApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScope:update')")
	@Operation(summary = "更新数据范围")
	@PutMapping("/update")
	@OpLog(name = "更新数据范围",module = OpLogConstants.Module.dataconstraint,type = OpLogConstants.Type.update)
	public SingleResponse<DataScopeVO> update(@RequestBody DataScopeUpdateCommand dataScopeUpdateCommand){
		dataScopeUpdateCommand.dcdo(DataConstraintConstants.data_object_data_constraint_data_scope,DataConstraintContext.Action.update.name());
		return iDataScopeApplicationService.update(dataScopeUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScope:update')")
	@Operation(summary = "数据范围更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DataScopeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDataScopeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScope:detail')")
	@Operation(summary = "数据范围详情展示")
	@GetMapping("/detail")
	public SingleResponse<DataScopeVO> queryDetail(IdCommand detailCommand){
		return iDataScopeRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScope:queryList')")
	@Operation(summary = "列表查询数据范围")
	@GetMapping("/list")
	public MultiResponse<DataScopeVO> queryList(DataScopeQueryListCommand dataScopeQueryListCommand){
		dataScopeQueryListCommand.dcdo(DataConstraintConstants.data_object_data_constraint_data_scope,DataConstraintContext.Action.query.name());
		return iDataScopeRepresentationApplicationService.queryList(dataScopeQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScope:pageQuery')")
	@Operation(summary = "分页查询数据范围")
	@GetMapping("/page")
	public PageResponse<DataScopeVO> pageQueryList(DataScopePageQueryCommand dataScopePageQueryCommand){
		dataScopePageQueryCommand.dcdo(DataConstraintConstants.data_object_data_constraint_data_scope,DataConstraintContext.Action.query.name());
		return iDataScopeRepresentationApplicationService.pageQuery(dataScopePageQueryCommand);
	}

}
