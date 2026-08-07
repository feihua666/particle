package com.particle.workflow.adapter.definition.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.workflow.client.definition.api.IWorkflowDefinitionApplicationService;
import com.particle.workflow.client.definition.api.representation.IWorkflowDefinitionRepresentationApplicationService;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionCreateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionUpdateCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionQueryListCommand;
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
 * 工作流定义后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Tag(name = "工作流定义pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/workflow_definition")
public class WorkflowDefinitionAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IWorkflowDefinitionApplicationService iWorkflowDefinitionApplicationService;
    @Autowired
    private IWorkflowDefinitionRepresentationApplicationService iWorkflowDefinitionRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:workflowDefinition:create')")
    @Operation(summary = "添加工作流定义")
    @PostMapping("/create")
    @OpLog(name = "添加工作流定义",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.create)
    public SingleResponse<WorkflowDefinitionVO> create(@RequestBody WorkflowDefinitionCreateCommand workflowDefinitionCreateCommand){
        return iWorkflowDefinitionApplicationService.create(workflowDefinitionCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinition:delete')")
    @Operation(summary = "删除工作流定义")
    @DeleteMapping("/delete")
    @OpLog(name = "删除工作流定义",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.delete)
    public SingleResponse<WorkflowDefinitionVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iWorkflowDefinitionApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinition:update')")
    @Operation(summary = "更新工作流定义")
    @PutMapping("/update")
    @OpLog(name = "更新工作流定义",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.update)
    public SingleResponse<WorkflowDefinitionVO> update(@RequestBody WorkflowDefinitionUpdateCommand workflowDefinitionUpdateCommand){
        workflowDefinitionUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iWorkflowDefinitionApplicationService.update(workflowDefinitionUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinition:update')")
    @Operation(summary = "工作流定义更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<WorkflowDefinitionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iWorkflowDefinitionRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinition:detail')")
    @Operation(summary = "工作流定义详情展示")
    @GetMapping("/detail")
    public SingleResponse<WorkflowDefinitionVO> queryDetail(CommonIdCommand detailCommand){
        return iWorkflowDefinitionRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinition:queryList')")
    @Operation(summary = "列表查询工作流定义")
    @GetMapping("/list")
    public MultiResponse<WorkflowDefinitionVO> queryList(WorkflowDefinitionQueryListCommand workflowDefinitionQueryListCommand){
        workflowDefinitionQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iWorkflowDefinitionRepresentationApplicationService.queryList(workflowDefinitionQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinition:pageQuery')")
    @Operation(summary = "分页查询工作流定义")
    @GetMapping("/page")
    public PageResponse<WorkflowDefinitionVO> pageQueryList(WorkflowDefinitionPageQueryCommand workflowDefinitionPageQueryCommand){
        workflowDefinitionPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iWorkflowDefinitionRepresentationApplicationService.pageQuery(workflowDefinitionPageQueryCommand);
    }
}
