package com.particle.global.workflow.command;

import com.particle.global.dto.basic.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * 从此节点运行命令
 * <p>
 * 替换原有的 WorkflowResumeFromNodeCommand。
 * 每次执行创建新实例，可选择性拷贝已有执行实例的数据。
 * </p>
 *
 * @author particle
 * @since 2026-05-01
 */
@Data
public class WorkflowExecuteFromNodeCommand extends Command {

    @NotNull(message = "工作流定义ID不能为空")
    @Schema(description = "工作流定义ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long definitionId;

    @Schema(description = "历史版本ID，null 表示最新版本")
    private Long historyId;

    @NotBlank(message = "起始节点ID不能为空")
    @Schema(description = "起始节点ID（从此节点开始执行）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startNodeId;

    @Schema(description = "结束节点ID（可选，非空时执行从 startNodeId 到 endNodeId 的路径，为空时只执行 startNodeId 单个节点）")
    private String endNodeId;

    @Schema(description = "输入变量")
    private Map<String, Object> inputVariables;

    @NotNull(message = "触发类型不能为空")
    @Schema(description = "触发类型: MANUAL/API/SCHEDULE", requiredMode = Schema.RequiredMode.REQUIRED)
    private String triggerType;

    @Schema(description = "是否同步等待执行完成（true=阻塞返回executionId，false=立即返回executionId异步执行）")
    private Boolean sync = false;

    @Schema(description = "数据来源执行ID（拷贝该实例的上游数据，null 表示不拷贝）")
    private Long copiedExecutionId;
}
