package com.particle.global.workflow.orchestrator;

import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.workflow.command.WorkflowExecuteCommand;
import com.particle.global.workflow.command.WorkflowExecuteFromNodeCommand;
import com.particle.global.workflow.command.WorkflowExecuteUpToNodeCommand;
import com.particle.global.workflow.command.WorkflowExecutionControlCommand;
import com.particle.global.workflow.repository.WorkflowExecutionRepository;
import com.particle.global.workflow.vo.ExecutionDetailVO;
import com.particle.global.workflow.vo.ExecutionVO;

/**
 * 工作流编排服务接口
 * <p>
 * 统一的流程编排入口，串联 dag 执行 + 持久化（通过 WorkflowExecutionRepository）。
 * 所有执行接口统一返回 executionId（ExecutionVO），通过 sync 参数控制同步/异步。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
public interface WorkflowOrchestrationService {

    // ==================== 完整执行 ====================

    /**
     * 执行工作流（同步/异步由 command.sync 决定）
     * <p>
     * sync=true：阻塞直到执行完成，返回 executionId
     * sync=false：立即返回 executionId，异步执行
     * </p>
     *
     * @param command 执行指令
     * @return ExecutionVO（含 executionId）
     */
    SingleResponse<ExecutionVO> execute(WorkflowExecuteCommand command);

    // ==================== 运行到此节点 ====================

    /**
     * 执行工作流到此节点为止（同步/异步由 command.sync 决定）
     *
     * @param command 执行指令
     * @return ExecutionVO（含 executionId）
     */
    SingleResponse<ExecutionVO> executeUpToNode(WorkflowExecuteUpToNodeCommand command);

    // ==================== 从此节点运行 ====================

    /**
     * 从此节点开始运行（替换原 resumeFromNode/断点续跑）
     * <p>
     * 每次调用创建新的执行实例，可选择性拷贝已有实例的数据。
     * </p>
     *
     * @param command 执行指令（含 startNodeId，可选 endNodeId）
     * @return ExecutionVO（含 executionId）
     */
    SingleResponse<ExecutionVO> executeFromNode(WorkflowExecuteFromNodeCommand command);

    // ==================== 执行控制 ====================

    /**
     * 暂停执行
     */
    Response pause(WorkflowExecutionControlCommand command);

    /**
     * 恢复执行（从 PAUSED 状态恢复）
     */
    Response resume(WorkflowExecutionControlCommand command);

    /**
     * 停止执行
     */
    Response stop(WorkflowExecutionControlCommand command);

    // ==================== 查询 ====================

    /**
     * 获取执行状态详情
     *
     * @param executionId 执行记录ID
     * @return 执行详情（含节点执行状态）
     */
    SingleResponse<ExecutionDetailVO> getExecutionInfo(Long executionId);
}
