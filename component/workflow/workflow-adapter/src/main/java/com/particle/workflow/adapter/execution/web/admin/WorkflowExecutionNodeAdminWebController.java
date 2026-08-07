package com.particle.workflow.adapter.execution.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.workflow.client.execution.api.IWorkflowExecutionNodeApplicationService;
import com.particle.workflow.client.execution.api.representation.IWorkflowExecutionNodeRepresentationApplicationService;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionNodeCreateCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionNodeVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionNodeUpdateCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodePageQueryCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodeQueryListCommand;
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
 * 工作流节点执行实例后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Tag(name = "工作流节点执行实例pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/workflow_execution_node")
public class WorkflowExecutionNodeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IWorkflowExecutionNodeApplicationService iWorkflowExecutionNodeApplicationService;
    @Autowired
    private IWorkflowExecutionNodeRepresentationApplicationService iWorkflowExecutionNodeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:workflowExecutionNode:create')")
    @Operation(summary = "添加工作流节点执行实例")
    @PostMapping("/create")
    @OpLog(name = "添加工作流节点执行实例",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.create)
    public SingleResponse<WorkflowExecutionNodeVO> create(@RequestBody WorkflowExecutionNodeCreateCommand workflowExecutionNodeCreateCommand){
        return iWorkflowExecutionNodeApplicationService.create(workflowExecutionNodeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecutionNode:delete')")
    @Operation(summary = "删除工作流节点执行实例")
    @DeleteMapping("/delete")
    @OpLog(name = "删除工作流节点执行实例",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.delete)
    public SingleResponse<WorkflowExecutionNodeVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iWorkflowExecutionNodeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecutionNode:update')")
    @Operation(summary = "更新工作流节点执行实例")
    @PutMapping("/update")
    @OpLog(name = "更新工作流节点执行实例",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.update)
    public SingleResponse<WorkflowExecutionNodeVO> update(@RequestBody WorkflowExecutionNodeUpdateCommand workflowExecutionNodeUpdateCommand){
        workflowExecutionNodeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iWorkflowExecutionNodeApplicationService.update(workflowExecutionNodeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecutionNode:update')")
    @Operation(summary = "工作流节点执行实例更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<WorkflowExecutionNodeVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iWorkflowExecutionNodeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecutionNode:detail')")
    @Operation(summary = "工作流节点执行实例详情展示")
    @GetMapping("/detail")
    public SingleResponse<WorkflowExecutionNodeVO> queryDetail(CommonIdCommand detailCommand){
        return iWorkflowExecutionNodeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecutionNode:queryList')")
    @Operation(summary = "列表查询工作流节点执行实例")
    @GetMapping("/list")
    public MultiResponse<WorkflowExecutionNodeVO> queryList(WorkflowExecutionNodeQueryListCommand workflowExecutionNodeQueryListCommand){
        workflowExecutionNodeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iWorkflowExecutionNodeRepresentationApplicationService.queryList(workflowExecutionNodeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecutionNode:pageQuery')")
    @Operation(summary = "分页查询工作流节点执行实例")
    @GetMapping("/page")
    public PageResponse<WorkflowExecutionNodeVO> pageQueryList(WorkflowExecutionNodePageQueryCommand workflowExecutionNodePageQueryCommand){
        workflowExecutionNodePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iWorkflowExecutionNodeRepresentationApplicationService.pageQuery(workflowExecutionNodePageQueryCommand);
    }
}
