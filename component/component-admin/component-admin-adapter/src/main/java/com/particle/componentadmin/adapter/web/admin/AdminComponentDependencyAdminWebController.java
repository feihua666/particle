package com.particle.componentadmin.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.componentadmin.client.api.IAdminComponentDependencyApplicationService;
import com.particle.componentadmin.client.api.representation.IAdminComponentDependencyRepresentationApplicationService;
import com.particle.componentadmin.client.dto.command.AdminComponentDependencyCreateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.componentadmin.client.dto.command.AdminComponentDependencyUpdateCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyPageQueryCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyQueryListCommand;
import com.particle.componentadmin.client.dto.command.ComponentAssignDependComponentCommand;
import com.particle.componentadmin.client.dto.command.DependComponentAssignComponentCommand;
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
 * 组件依赖关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Tag(name = "组件依赖关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/admin_component_dependency")
public class AdminComponentDependencyAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAdminComponentDependencyApplicationService iAdminComponentDependencyApplicationService;
    @Autowired
    private IAdminComponentDependencyRepresentationApplicationService iAdminComponentDependencyRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:create')")
    @Operation(summary = "添加组件依赖关系")
    @PostMapping("/create")
    @OpLog(name = "添加组件依赖关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<AdminComponentDependencyVO> create(@RequestBody AdminComponentDependencyCreateCommand adminComponentDependencyCreateCommand){
        return iAdminComponentDependencyApplicationService.create(adminComponentDependencyCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:delete')")
    @Operation(summary = "删除组件依赖关系")
    @DeleteMapping("/delete")
    @OpLog(name = "删除组件依赖关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<AdminComponentDependencyVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAdminComponentDependencyApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:update')")
    @Operation(summary = "更新组件依赖关系")
    @PutMapping("/update")
    @OpLog(name = "更新组件依赖关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<AdminComponentDependencyVO> update(@RequestBody AdminComponentDependencyUpdateCommand adminComponentDependencyUpdateCommand){
        adminComponentDependencyUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAdminComponentDependencyApplicationService.update(adminComponentDependencyUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:update')")
    @Operation(summary = "组件依赖关系更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AdminComponentDependencyVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iAdminComponentDependencyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:detail')")
    @Operation(summary = "组件依赖关系详情展示")
    @GetMapping("/detail")
    public SingleResponse<AdminComponentDependencyVO> queryDetail(CommonIdCommand detailCommand){
        return iAdminComponentDependencyRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:queryList')")
    @Operation(summary = "列表查询组件依赖关系")
    @GetMapping("/list")
    public MultiResponse<AdminComponentDependencyVO> queryList(AdminComponentDependencyQueryListCommand adminComponentDependencyQueryListCommand){
        adminComponentDependencyQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAdminComponentDependencyRepresentationApplicationService.queryList(adminComponentDependencyQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:pageQuery')")
    @Operation(summary = "分页查询组件依赖关系")
    @GetMapping("/page")
    public PageResponse<AdminComponentDependencyVO> pageQueryList(AdminComponentDependencyPageQueryCommand adminComponentDependencyPageQueryCommand){
        adminComponentDependencyPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAdminComponentDependencyRepresentationApplicationService.pageQuery(adminComponentDependencyPageQueryCommand);
    }

    @Operation(summary = "源组件分配依赖组件")
    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:componentAssignDependComponent')")
    @PostMapping("/component/assign/dependComponent")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "源组件分配依赖组件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.relAsign)
    public Response componentAssignDependComponent(@RequestBody ComponentAssignDependComponentCommand cf) {
        return iAdminComponentDependencyApplicationService.componentAssignDependComponent(cf);
    }

    @Operation(summary = "根据源组件ID查询已分配的依赖组件id")
    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:queryDependComponentIdsByComponentId')")
    @GetMapping("/queryDependComponentIdsByComponentId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryDependComponentIdsByComponentId(CommonIdCommand commonIdCommand) {
        return iAdminComponentDependencyRepresentationApplicationService.queryDependComponentIdsByComponentId(commonIdCommand);
    }

    @Operation(summary = "清空源组件下的所有依赖组件")
    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:deleteByComponentId')")
    @DeleteMapping("/deleteByComponentId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空源组件下的所有依赖组件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public Response deleteByComponentId(@RequestBody CommonIdCommand commonIdCommand) {
        return iAdminComponentDependencyApplicationService.deleteByComponentId(commonIdCommand);
    }


    @Operation(summary = "依赖组件分配源组件")
    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:dependComponentAssignComponent')")
    @PostMapping("/dependComponent/assign/component")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "依赖组件分配源组件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.relAsign)
    public Response dependComponentAssignComponent(@RequestBody DependComponentAssignComponentCommand cf) {
        return iAdminComponentDependencyApplicationService.dependComponentAssignComponent(cf);
    }

    @Operation(summary = "根据依赖组件id查询已分配的源组件id")
    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:queryComponentIdsByDependComponentId')")
    @GetMapping("/queryComponentIdsByDependComponentId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryByDependComponentId(CommonIdCommand commonIdCommand) {
        return iAdminComponentDependencyRepresentationApplicationService.queryComponentIdsByDependComponentId(commonIdCommand);

    }

    @Operation(summary = "清空依赖组件下的所有源组件")
    @PreAuthorize("hasAuthority('admin:web:adminComponentDependency:deleteByDependComponentId')")
    @DeleteMapping("/deleteByDependComponentId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空依赖组件下的所有源组件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public Response deleteByDependComponentId(@RequestBody CommonIdCommand commonIdCommand) {
        return iAdminComponentDependencyApplicationService.deleteByDependComponentId(commonIdCommand);
    }

}
