package com.particle.global.workflow.command;

import com.particle.global.dto.basic.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 工作流执行控制指令（pause/resume/stop 通用）
 *
 * @author particle
 * @since 2026-05-01
 */
@Data
public class WorkflowExecutionControlCommand extends Command {

    @NotNull(message = "执行记录ID不能为空")
    @Schema(description = "执行记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long executionId;
}
