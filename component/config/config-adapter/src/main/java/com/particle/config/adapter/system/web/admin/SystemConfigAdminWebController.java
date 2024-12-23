package com.particle.config.adapter.system.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.config.client.system.api.ISystemConfigApplicationService;
import com.particle.config.client.system.api.representation.ISystemConfigRepresentationApplicationService;
import com.particle.config.client.system.dto.command.SystemConfigCreateCommand;
import com.particle.config.client.system.dto.command.SystemConfigUpdateCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigPageQueryCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigQueryListCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
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
 * 系统参数配置后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Tag(name = "系统参数配置pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/system_config")
public class SystemConfigAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ISystemConfigApplicationService iSystemConfigApplicationService;
	@Autowired
	private ISystemConfigRepresentationApplicationService iSystemConfigRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:systemConfig:create')")
	@Operation(summary = "添加系统参数配置")
	@PostMapping("/create")
	@OpLog(name = "添加系统参数配置",module = OpLogConstants.Module.config,type = OpLogConstants.Type.create)
	public SingleResponse<SystemConfigVO> create(@RequestBody SystemConfigCreateCommand systemConfigCreateCommand){
		return iSystemConfigApplicationService.create(systemConfigCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:systemConfig:delete')")
	@Operation(summary = "删除系统参数配置")
	@DeleteMapping("/delete")
	@OpLog(name = "删除系统参数配置",module = OpLogConstants.Module.config,type = OpLogConstants.Type.delete)
	public SingleResponse<SystemConfigVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_config_system_config, DataConstraintContext.Action.delete.name());
		return iSystemConfigApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:systemConfig:update')")
	@Operation(summary = "更新系统参数配置")
	@PutMapping("/update")
	@OpLog(name = "更新系统参数配置",module = OpLogConstants.Module.config,type = OpLogConstants.Type.update)
	public SingleResponse<SystemConfigVO> update(@RequestBody SystemConfigUpdateCommand systemConfigUpdateCommand){
		systemConfigUpdateCommand.dcdo(DataConstraintConstants.data_object_config_system_config,DataConstraintContext.Action.update.name());
		return iSystemConfigApplicationService.update(systemConfigUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:systemConfig:update')")
	@Operation(summary = "系统参数配置更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<SystemConfigVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iSystemConfigRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:systemConfig:detail')")
	@Operation(summary = "系统参数配置详情展示")
	@GetMapping("/detail")
	public SingleResponse<SystemConfigVO> queryDetail(IdCommand detailCommand){
		return iSystemConfigRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:systemConfig:queryList')")
	@Operation(summary = "列表查询系统参数配置")
	@GetMapping("/list")
	public MultiResponse<SystemConfigVO> queryList(SystemConfigQueryListCommand systemConfigQueryListCommand){
		systemConfigQueryListCommand.dcdo(DataConstraintConstants.data_object_config_system_config,DataConstraintContext.Action.query.name());
		return iSystemConfigRepresentationApplicationService.queryList(systemConfigQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:systemConfig:pageQuery')")
	@Operation(summary = "分页查询系统参数配置")
	@GetMapping("/page")
	public PageResponse<SystemConfigVO> pageQueryList(SystemConfigPageQueryCommand systemConfigPageQueryCommand){
		systemConfigPageQueryCommand.dcdo(DataConstraintConstants.data_object_config_system_config,DataConstraintContext.Action.query.name());
		return iSystemConfigRepresentationApplicationService.pageQuery(systemConfigPageQueryCommand);
	}

}
