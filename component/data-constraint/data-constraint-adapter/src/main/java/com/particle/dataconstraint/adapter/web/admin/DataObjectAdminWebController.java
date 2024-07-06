package com.particle.dataconstraint.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.dataconstraint.client.api.IDataObjectApplicationService;
import com.particle.dataconstraint.client.api.representation.IDataObjectRepresentationApplicationService;
import com.particle.dataconstraint.client.dto.command.DataObjectCreateCommand;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.client.dto.command.DataObjectUpdateCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectPageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
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
import com.particle.global.dataaudit.op.OpLog;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 数据对象后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Tag(name = "数据对象pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_object")
public class DataObjectAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataObjectApplicationService iDataObjectApplicationService;
	@Autowired
	private IDataObjectRepresentationApplicationService iDataObjectRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dataObject:create')")
	@Operation(summary = "添加数据对象")
	@PostMapping("/create")
	@OpLog(name = "添加数据对象",module = OpLogConstants.Module.dataconstraint,type = OpLogConstants.Type.create)
	public SingleResponse<DataObjectVO> create(@RequestBody DataObjectCreateCommand dataObjectCreateCommand){
		return iDataObjectApplicationService.create(dataObjectCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataObject:delete')")
	@Operation(summary = "删除数据对象")
	@DeleteMapping("/delete")
	@OpLog(name = "删除数据对象",module = OpLogConstants.Module.dataconstraint,type = OpLogConstants.Type.delete)
	public SingleResponse<DataObjectVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_data_constraint_data_object, DataConstraintContext.Action.delete.name());
		return iDataObjectApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataObject:update')")
	@Operation(summary = "更新数据对象")
	@PutMapping("/update")
	@OpLog(name = "更新数据对象",module = OpLogConstants.Module.dataconstraint,type = OpLogConstants.Type.update)
	public SingleResponse<DataObjectVO> update(@RequestBody DataObjectUpdateCommand dataObjectUpdateCommand){
		dataObjectUpdateCommand.dcdo(DataConstraintConstants.data_object_data_constraint_data_object,DataConstraintContext.Action.update.name());
		return iDataObjectApplicationService.update(dataObjectUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataObject:update')")
	@Operation(summary = "数据对象更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DataObjectVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDataObjectRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataObject:detail')")
	@Operation(summary = "数据对象详情展示")
	@GetMapping("/detail")
	public SingleResponse<DataObjectVO> queryDetail(IdCommand detailCommand){
		return iDataObjectRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataObject:queryList')")
	@Operation(summary = "列表查询数据对象")
	@GetMapping("/list")
	public MultiResponse<DataObjectVO> queryList(DataObjectQueryListCommand dataObjectQueryListCommand){
		dataObjectQueryListCommand.dcdo(DataConstraintConstants.data_object_data_constraint_data_object,DataConstraintContext.Action.query.name());
		return iDataObjectRepresentationApplicationService.queryList(dataObjectQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataObject:pageQuery')")
	@Operation(summary = "分页查询数据对象")
	@GetMapping("/page")
	public PageResponse<DataObjectVO> pageQueryList(DataObjectPageQueryCommand dataObjectPageQueryCommand){
		dataObjectPageQueryCommand.dcdo(DataConstraintConstants.data_object_data_constraint_data_object,DataConstraintContext.Action.query.name());
		return iDataObjectRepresentationApplicationService.pageQuery(dataObjectPageQueryCommand);
	}

}