package com.particle.area.adapter.web.admin;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.area.client.api.representation.IAreaRepresentationApplicationService;
import com.particle.area.client.dto.command.AreaCreateCommand;
import com.particle.area.client.dto.command.AreaUpdateCommand;
import com.particle.area.client.dto.command.representation.AreaPageQueryCommand;
import com.particle.area.client.dto.command.representation.AreaQueryListCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
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
 * 区域后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Tag(name = "区域pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/area")
public class AreaAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAreaApplicationService iAreaApplicationService;
	@Autowired
	private IAreaRepresentationApplicationService iAreaRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:area:create')")
	@Operation(summary = "添加区域")
	@PostMapping("/create")
	@OpLog(name = "添加区域",module = OpLogConstants.Module.area,type = OpLogConstants.Type.create)
	public SingleResponse<AreaVO> create(@RequestBody AreaCreateCommand areaCreateCommand){
		return iAreaApplicationService.create(areaCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:delete')")
	@Operation(summary = "删除区域")
	@DeleteMapping("/delete")
	@OpLog(name = "删除区域",module = OpLogConstants.Module.area,type = OpLogConstants.Type.delete)
	public SingleResponse<AreaVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_area_area,DataConstraintContext.Action.delete.name());
		return iAreaApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:update')")
	@Operation(summary = "更新区域")
	@PutMapping("/update")
	@OpLog(name = "更新区域",module = OpLogConstants.Module.area,type = OpLogConstants.Type.update)
	public SingleResponse<AreaVO> update(@RequestBody AreaUpdateCommand areaUpdateCommand){
		areaUpdateCommand.dcdo(DataConstraintConstants.data_object_area_area, DataConstraintContext.Action.update.name());
		return iAreaApplicationService.update(areaUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:update')")
	@Operation(summary = "区域更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<AreaVO> queryDetailForUpdate(IdCommand areaQueryDetailForUpdateCommand){
		return iAreaRepresentationApplicationService.queryDetailForUpdate(areaQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:detail')")
	@Operation(summary = "区域详情展示")
	@GetMapping("/detail")
	public SingleResponse<AreaVO> queryDetail(IdCommand areaQueryDetailCommand){
		return iAreaRepresentationApplicationService.queryDetail(areaQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:queryList')")
	@Operation(summary = "列表查询区域")
	@GetMapping("/list")
	public MultiResponse<AreaVO> queryList(AreaQueryListCommand areaQueryListCommand){
		areaQueryListCommand.dcdo(DataConstraintConstants.data_object_area_area,DataConstraintContext.Action.query.name());
		return iAreaRepresentationApplicationService.queryList(areaQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:pageQuery')")
	@Operation(summary = "分页查询区域")
	@GetMapping("/page")
	public PageResponse<AreaVO> pageQueryList(AreaPageQueryCommand areaPageQueryCommand){
		areaPageQueryCommand.dcdo(DataConstraintConstants.data_object_area_area,DataConstraintContext.Action.query.name());
		return iAreaRepresentationApplicationService.pageQuery(areaPageQueryCommand);
	}

}
