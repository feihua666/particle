package com.particle.global.workflow.command;

import com.particle.global.dto.basic.Command;
import com.particle.global.workflow.enums.WorkflowTriggerType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * 工作流执行指令
 *
 * @author particle
 * @since 2026-05-01
 */
@Data
public class WorkflowExecuteCommand extends Command {

    @NotNull(message = "工作流定义ID不能为空")
    @Schema(description = "工作流定义ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long definitionId;

    @Schema(description = "版本历史ID（null则使用最新版本）")
    private Long historyId;

    @Schema(description = "输入变量")
    private Map<String, Object> inputVariables;

    /**
     * 触发类型
     * 参考 {@link WorkflowTriggerType}
     */
    @NotNull(message = "触发类型不能为空")
    @Schema(description = "触发类型：MANUAL/API/SCHEDULE", requiredMode = Schema.RequiredMode.REQUIRED)
    private String triggerType;

    @Schema(description = "是否同步等待执行完成（true=阻塞直到完成返回executionId，false=立即返回executionId异步执行）")
    private Boolean sync = false;
}
