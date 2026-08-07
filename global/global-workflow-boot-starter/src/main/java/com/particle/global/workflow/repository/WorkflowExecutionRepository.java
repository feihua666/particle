package com.particle.global.workflow.repository;

import com.particle.global.workflow.dto.ExecutionDTO;
import com.particle.global.workflow.dto.NodeExecutionDTO;
import com.particle.global.workflow.dto.WorkflowGraphDTO;

import java.util.List;
import java.util.Map;

/**
 * 工作流执行数据访问接口
 * <p>
 * 统一使用 get（查）和 update（改）动词，create 用于新建。
 * 所有查询方法返回 DTO 对象，不再传递 JSON 字符串。
 * </p>
 *
 * @author particle
 * @since 2026-05-02
 */
public interface WorkflowExecutionRepository {

    // ==================== 定义 ====================

    /**
     * 加载工作流图数据
     * <p>
     * 从数据库 graphDataJson 反序列化为 WorkflowGraphDTO。
     * 转换为 DagDefinition 由调用方（Orchestration 层）通过 WorkflowGraphConverter 完成。
     * </p>
     *
     * @param definitionId 工作流定义ID
     * @param historyId    历史版本ID（null 则使用最新版本）
     * @return WorkflowGraphDTO
     */
    WorkflowGraphDTO getWorkflowGraph(Long definitionId, Long historyId);

    // ==================== 执行记录 ====================

    /**
     * 创建执行记录
     *
     * @param definitionId     工作流定义ID
     * @param historyId       历史版本ID
     * @param triggerType     触发类型：MANUAL/API/SCHEDULE
     * @param copiedExecutionId 数据来源执行ID（非空时自动拷贝来源数据），null 不拷贝
     * @return 新建的执行记录ID
     */
    Long createExecution(Long definitionId, Long historyId,
                         String triggerType, Long copiedExecutionId);

    /**
     * 获取执行记录（含 context，不含 nodeExecutions）
     *
     * @param executionId 执行记录ID
     * @return 执行记录 DTO
     */
    ExecutionDTO getExecution(Long executionId);

    /**
     * 更新执行记录状态
     *
     * @param executionId 执行记录ID
     * @param status      新状态
     */
    void updateExecutionStatus(Long executionId, String status);

    /**
     * 更新执行上下文
     *
     * @param executionId  执行记录ID
     * @param context     上下文数据
     */
    void updateExecutionContext(Long executionId, Map<String, Object> context);

    // ==================== 节点执行 ====================

    /**
     * 获取单个节点执行记录
     *
     * @param executionId 执行记录ID
     * @param nodeId      节点ID
     * @return 节点执行记录 DTO
     */
    NodeExecutionDTO getNodeExecution(Long executionId, String nodeId);

    /**
     * 获取所有节点执行记录
     *
     * @param executionId 执行记录ID
     * @return 节点执行记录 DTO 列表
     */
    List<NodeExecutionDTO> getNodeExecutions(Long executionId);

    /**
     * 更新节点执行状态（upsert：不存在时创建，存在时更新）
     * <p>
     * 调用顺序：先 status=RUNNING（创建），再 COMPLETED/FAILED（更新）
     * </p>
     *
     * @param executionId 执行记录ID
     * @param nodeId      节点ID
     * @param status      状态
     * @param input       输入数据（null 时不更新）
     * @param output      输出数据（null 时不更新）
     * @param errorMsg    错误信息（null 时不更新）
     */
    void updateNodeExecution(Long executionId, String nodeId, String status,
                             Map<String, Object> input,
                             Map<String, Object> output,
                             String errorMsg);
}
