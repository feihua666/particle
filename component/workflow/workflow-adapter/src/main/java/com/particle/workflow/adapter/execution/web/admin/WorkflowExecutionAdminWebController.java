package com.particle.workflow.adapter.execution.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.workflow.client.execution.api.IWorkflowExecutionApplicationService;
import com.particle.workflow.client.execution.api.representation.IWorkflowExecutionRepresentationApplicationService;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionCreateCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionUpdateCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionPageQueryCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionQueryListCommand;
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
 * 工作流执行实例后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Tag(name = "工作流执行实例pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/workflow_execution")
public class WorkflowExecutionAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IWorkflowExecutionApplicationService iWorkflowExecutionApplicationService;
    @Autowired
    private IWorkflowExecutionRepresentationApplicationService iWorkflowExecutionRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:workflowExecution:create')")
    @Operation(summary = "添加工作流执行实例")
    @PostMapping("/create")
    @OpLog(name = "添加工作流执行实例",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.create)
    public SingleResponse<WorkflowExecutionVO> create(@RequestBody WorkflowExecutionCreateCommand workflowExecutionCreateCommand){
        return iWorkflowExecutionApplicationService.create(workflowExecutionCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecution:delete')")
    @Operation(summary = "删除工作流执行实例")
    @DeleteMapping("/delete")
    @OpLog(name = "删除工作流执行实例",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.delete)
    public SingleResponse<WorkflowExecutionVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iWorkflowExecutionApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecution:update')")
    @Operation(summary = "更新工作流执行实例")
    @PutMapping("/update")
    @OpLog(name = "更新工作流执行实例",module = OpLogConstants.Module.workflow,type = OpLogConstants.Type.update)
    public SingleResponse<WorkflowExecutionVO> update(@RequestBody WorkflowExecutionUpdateCommand workflowExecutionUpdateCommand){
        workflowExecutionUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iWorkflowExecutionApplicationService.update(workflowExecutionUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecution:update')")
    @Operation(summary = "工作流执行实例更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<WorkflowExecutionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iWorkflowExecutionRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecution:detail')")
    @Operation(summary = "工作流执行实例详情展示")
    @GetMapping("/detail")
    public SingleResponse<WorkflowExecutionVO> queryDetail(CommonIdCommand detailCommand){
        return iWorkflowExecutionRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecution:queryList')")
    @Operation(summary = "列表查询工作流执行实例")
    @GetMapping("/list")
    public MultiResponse<WorkflowExecutionVO> queryList(WorkflowExecutionQueryListCommand workflowExecutionQueryListCommand){
        workflowExecutionQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iWorkflowExecutionRepresentationApplicationService.queryList(workflowExecutionQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:workflowExecution:pageQuery')")
    @Operation(summary = "分页查询工作流执行实例")
    @GetMapping("/page")
    public PageResponse<WorkflowExecutionVO> pageQueryList(WorkflowExecutionPageQueryCommand workflowExecutionPageQueryCommand){
        workflowExecutionPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iWorkflowExecutionRepresentationApplicationService.pageQuery(workflowExecutionPageQueryCommand);
    }
}
