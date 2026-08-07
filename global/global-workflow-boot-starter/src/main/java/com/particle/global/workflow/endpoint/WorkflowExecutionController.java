package com.particle.global.workflow.endpoint;

import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.workflow.command.WorkflowExecuteCommand;
import com.particle.global.workflow.command.WorkflowExecuteFromNodeCommand;
import com.particle.global.workflow.command.WorkflowExecuteUpToNodeCommand;
import com.particle.global.workflow.command.WorkflowExecutionControlCommand;
import com.particle.global.workflow.orchestrator.WorkflowOrchestrationService;
import com.particle.global.workflow.vo.ExecutionDetailVO;
import com.particle.global.workflow.vo.ExecutionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 工作流执行 REST API
 * <p>
 * 所有执行接口统一返回 ExecutionVO（含 executionId），同步/异步由 command.sync 参数控制。
 * 执行状态通过 GET /{executionId} 查询 ExecutionDetailVO。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
@Tag(name = "工作流执行管理")
@Slf4j
@RestController
@RequestMapping("/api/workflow/execution")
public class WorkflowExecutionController {

    @Autowired
    private WorkflowOrchestrationService orchestrationService;

    /**
     * 执行工作流
     * <p>
     * sync=true：阻塞直到执行完成，返回 executionId
     * sync=false：立即返回 executionId，异步执行
     * </p>
     */
    @Operation(summary = "执行工作流")
    @PostMapping("/execute")
    public SingleResponse<ExecutionVO> execute(@RequestBody @Valid WorkflowExecuteCommand command) {
        log.info("执行工作流: definitionId={}, triggerType={}, sync={}",
                command.getDefinitionId(), command.getTriggerType(), command.getSync());
        return orchestrationService.execute(command);
    }

    /**
     * 运行到此节点
     */
    @Operation(summary = "运行到此节点")
    @PostMapping("/execute-up-to-node")
    public SingleResponse<ExecutionVO> executeUpToNode(@RequestBody @Valid WorkflowExecuteUpToNodeCommand command) {
        log.info("运行到此节点: definitionId={}, endNodeId={}, triggerType={}, sync={}",
                command.getDefinitionId(), command.getEndNodeId(),
                command.getTriggerType(), command.getSync());
        return orchestrationService.executeUpToNode(command);
    }

    /**
     * 从此节点运行
     * <p>
     * 每次调用创建新的执行实例，可选择性拷贝已有实例的数据。
     * </p>
     */
    @Operation(summary = "从此节点运行")
    @PostMapping("/execute-from-node")
    public SingleResponse<ExecutionVO> executeFromNode(@RequestBody @Valid WorkflowExecuteFromNodeCommand command) {
        log.info("从此节点运行: definitionId={}, startNodeId={}, endNodeId={}, triggerType={}, sync={}, copiedExecutionId={}",
                command.getDefinitionId(), command.getStartNodeId(), command.getEndNodeId(),
                command.getTriggerType(), command.getSync(), command.getCopiedExecutionId());
        return orchestrationService.executeFromNode(command);
    }

    /**
     * 暂停执行
     */
    @Operation(summary = "暂停执行")
    @PostMapping("/{executionId}/pause")
    public Response pause(@PathVariable Long executionId) {
        log.info("暂停执行: executionId={}", executionId);
        WorkflowExecutionControlCommand command = new WorkflowExecutionControlCommand();
        command.setExecutionId(executionId);
        return orchestrationService.pause(command);
    }

    /**
     * 恢复执行（从 PAUSED 状态恢复）
     */
    @Operation(summary = "恢复执行")
    @PostMapping("/{executionId}/resume")
    public Response resume(@PathVariable Long executionId) {
        log.info("恢复执行: executionId={}", executionId);
        WorkflowExecutionControlCommand command = new WorkflowExecutionControlCommand();
        command.setExecutionId(executionId);
        return orchestrationService.resume(command);
    }

    /**
     * 停止执行
     */
    @Operation(summary = "停止执行")
    @PostMapping("/{executionId}/stop")
    public Response stop(@PathVariable Long executionId) {
        log.info("停止执行: executionId={}", executionId);
        WorkflowExecutionControlCommand command = new WorkflowExecutionControlCommand();
        command.setExecutionId(executionId);
        return orchestrationService.stop(command);
    }

    /**
     * 查询执行状态详情
     */
    @Operation(summary = "查询执行状态详情")
    @GetMapping("/{executionId}")
    public SingleResponse<ExecutionDetailVO> getExecutionInfo(@PathVariable Long executionId) {
        return orchestrationService.getExecutionInfo(executionId);
    }
}
