package com.particle.componentadmin.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.componentadmin.client.api.IAdminComponentApplicationService;
import com.particle.componentadmin.client.api.representation.IAdminComponentRepresentationApplicationService;
import com.particle.componentadmin.client.dto.command.AdminComponentCreateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.componentadmin.client.dto.command.AdminComponentUpdateCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentPageQueryCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
/**
 * <p>
 * 组件后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Tag(name = "组件pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/admin_component")
public class AdminComponentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAdminComponentApplicationService iAdminComponentApplicationService;
    @Autowired
    private IAdminComponentRepresentationApplicationService iAdminComponentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:adminComponent:create')")
    @Operation(summary = "添加组件")
    @PostMapping("/create")
    @OpLog(name = "添加组件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<AdminComponentVO> create(@RequestBody AdminComponentCreateCommand adminComponentCreateCommand){
        return iAdminComponentApplicationService.create(adminComponentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponent:delete')")
    @Operation(summary = "删除组件")
    @DeleteMapping("/delete")
    @OpLog(name = "删除组件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<AdminComponentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAdminComponentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponent:update')")
    @Operation(summary = "更新组件")
    @PutMapping("/update")
    @OpLog(name = "更新组件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<AdminComponentVO> update(@RequestBody AdminComponentUpdateCommand adminComponentUpdateCommand){
        adminComponentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAdminComponentApplicationService.update(adminComponentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponent:update')")
    @Operation(summary = "组件更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AdminComponentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iAdminComponentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponent:detail')")
    @Operation(summary = "组件详情展示")
    @GetMapping("/detail")
    public SingleResponse<AdminComponentVO> queryDetail(IdCommand detailCommand){
        return iAdminComponentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponent:queryList')")
    @Operation(summary = "列表查询组件")
    @GetMapping("/list")
    public MultiResponse<AdminComponentVO> queryList(AdminComponentQueryListCommand adminComponentQueryListCommand){
        adminComponentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAdminComponentRepresentationApplicationService.queryList(adminComponentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponent:pageQuery')")
    @Operation(summary = "分页查询组件")
    @GetMapping("/page")
    public PageResponse<AdminComponentVO> pageQueryList(AdminComponentPageQueryCommand adminComponentPageQueryCommand){
        adminComponentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAdminComponentRepresentationApplicationService.pageQuery(adminComponentPageQueryCommand);
    }
}