package com.particle.global.workflow.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 执行记录详情 VO（继承 ExecutionVO）
 * <p>
 * 查询执行状态时返回此对象，包含完整信息。
 * 替代原来的 WorkflowExecutionInfoVO + WorkflowExecutionResultVO。
 * </p>
 *
 * @author particle
 * @since 2026-05-02
 */
@Data
public class ExecutionDetailVO extends ExecutionVO {

    @Schema(description = "工作流定义ID")
    private Long definitionId;

    @Schema(description = "执行状态值：PENDING/RUNNING/COMPLETED/FAILED/PAUSED/STOPPED")
    private String statusValue;

    @Schema(description = "执行状态名称")
    private String statusName;

    @Schema(description = "触发类型值：MANUAL/API/SCHEDULE")
    private String triggerTypeValue;

    @Schema(description = "触发类型名称")
    private String triggerTypeName;

    @Schema(description = "当前节点ID（运行时有效）")
    private String currentNodeId;

    @Schema(description = "开始时间")
    private LocalDateTime startAt;

    @Schema(description = "结束时间")
    private LocalDateTime endAt;

    @Schema(description = "错误信息")
    private String errorMsg;

    @Schema(description = "数据来源执行ID")
    private Long copiedExecutionId;

    @Schema(description = "节点执行状态")
    private Map<String, NodeExecutionDetailVO> nodeExecutions;
}
