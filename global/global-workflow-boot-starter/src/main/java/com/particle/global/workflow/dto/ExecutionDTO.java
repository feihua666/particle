package com.particle.global.workflow.dto;

import com.particle.global.workflow.repository.WorkflowExecutionRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 执行记录 DTO
 * <p>
 * 不含 nodeExecutions，避免执行时频繁查询浪费。
 * 节点执行记录通过 {@link WorkflowExecutionRepository#getNodeExecutions(Long)} 单独查询。
 * </p>
 *
 * @author particle
 * @since 2026-05-02
 */
@Data
public class ExecutionDTO {

    @Schema(description = "执行记录ID")
    private Long executionId;

    @Schema(description = "工作流定义ID")
    private Long definitionId;

    @Schema(description = "历史版本ID")
    private Long historyId;

    @Schema(description = "触发类型值：MANUAL/API/SCHEDULE")
    private String triggerTypeValue;

    @Schema(description = "执行状态值：PENDING/RUNNING/COMPLETED/FAILED/PAUSED/STOPPED")
    private String statusValue;

    @Schema(description = "执行状态名称")
    private String statusName;

    @Schema(description = "当前节点ID（运行时有效）")
    private String currentNodeId;

    @Schema(description = "开始时间")
    private LocalDateTime startAt;

    @Schema(description = "结束时间")
    private LocalDateTime endAt;

    @Schema(description = "错误信息")
    private String errorMsg;

    @Schema(description = "数据来源执行ID（从此节点运行时拷贝的数据来源）")
    private Long copiedExecutionId;

    @Schema(description = "执行上下文")
    private Map<String, Object> context;
}
