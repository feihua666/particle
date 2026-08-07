package com.particle.workflow.adapter.definition.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.workflow.client.definition.api.IWorkflowDefinitionHistoryApplicationService;
import com.particle.workflow.client.definition.api.representation.IWorkflowDefinitionHistoryRepresentationApplicationService;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryCreateCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryCreateDraftCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryUpdateCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryQueryListCommand;
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
 * 工作流定义历史后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Tag(name = "工作流定义历史pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/workflow_definition_history")
public class WorkflowDefinitionHistoryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IWorkflowDefinitionHistoryApplicationService iWorkflowDefinitionHistoryApplicationService;
    @Autowired
    private IWorkflowDefinitionHistoryRepresentationApplicationService iWorkflowDefinitionHistoryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:workflowDefinitionHistory:create')")
    @Operation(summary = "添加工作流定义历史")
    @PostMapping("/create")
    @OpLog(name = "添加工作流定义历史",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.create)
    public SingleResponse<WorkflowDefinitionHistoryVO> create(@RequestBody WorkflowDefinitionHistoryCreateCommand workflowDefinitionHistoryCreateCommand){
        return iWorkflowDefinitionHistoryApplicationService.create(workflowDefinitionHistoryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinitionHistory:delete')")
    @Operation(summary = "删除工作流定义历史")
    @DeleteMapping("/delete")
    @OpLog(name = "删除工作流定义历史",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.delete)
    public SingleResponse<WorkflowDefinitionHistoryVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iWorkflowDefinitionHistoryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinitionHistory:update')")
    @Operation(summary = "更新工作流定义历史")
    @PutMapping("/update")
    @OpLog(name = "更新工作流定义历史",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.update)
    public SingleResponse<WorkflowDefinitionHistoryVO> update(@RequestBody WorkflowDefinitionHistoryUpdateCommand workflowDefinitionHistoryUpdateCommand){
        workflowDefinitionHistoryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iWorkflowDefinitionHistoryApplicationService.update(workflowDefinitionHistoryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinitionHistory:update')")
    @Operation(summary = "工作流定义历史更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<WorkflowDefinitionHistoryVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iWorkflowDefinitionHistoryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinitionHistory:detail')")
    @Operation(summary = "工作流定义历史详情展示")
    @GetMapping("/detail")
    public SingleResponse<WorkflowDefinitionHistoryVO> queryDetail(CommonIdCommand detailCommand){
        return iWorkflowDefinitionHistoryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinitionHistory:queryList')")
    @Operation(summary = "列表查询工作流定义历史")
    @GetMapping("/list")
    public MultiResponse<WorkflowDefinitionHistoryVO> queryList(WorkflowDefinitionHistoryQueryListCommand workflowDefinitionHistoryQueryListCommand){
        workflowDefinitionHistoryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iWorkflowDefinitionHistoryRepresentationApplicationService.queryList(workflowDefinitionHistoryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinitionHistory:pageQuery')")
    @Operation(summary = "分页查询工作流定义历史")
    @GetMapping("/page")
    public PageResponse<WorkflowDefinitionHistoryVO> pageQueryList(WorkflowDefinitionHistoryPageQueryCommand workflowDefinitionHistoryPageQueryCommand){
        workflowDefinitionHistoryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iWorkflowDefinitionHistoryRepresentationApplicationService.pageQuery(workflowDefinitionHistoryPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowDefinitionHistory:createDraft')")
    @Operation(summary = "添加工作流定义历史草稿")
    @PostMapping("/createDraft")
    @OpLog(name = "添加工作流定义历史草稿",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.create)
    public SingleResponse<WorkflowDefinitionHistoryVO> createDraft(@RequestBody WorkflowDefinitionHistoryCreateDraftCommand workflowDefinitionHistoryCreateDraftCommand){
        return iWorkflowDefinitionHistoryApplicationService.createDraft(workflowDefinitionHistoryCreateDraftCommand);
    }
}
