package com.particle.scheduler.client.temptask.dto.command.control;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 任务计划临时任务 日志指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-29 21:09:08
 */
@Data
@Schema
public class SchedulerTempTaskLogBaseCommand extends AbstractBaseCommand {

    @NotNull(message = "任务运行记录id 不能为空")
    @Schema(description = "任务运行记录id",requiredMode = Schema.RequiredMode.REQUIRED)
    protected Long id;

    @NotEmpty(message = "日志内容 不能为空")
    @Schema(description = "日志内容",requiredMode = Schema.RequiredMode.REQUIRED)
    protected String message;

    public SchedulerTempTaskLogCommand toLogCommand(String level) {
        return SchedulerTempTaskLogCommand.create(id, level, message);
    }

    public static SchedulerTempTaskLogBaseCommand create(Long id, String message) {
        SchedulerTempTaskLogBaseCommand command = new SchedulerTempTaskLogBaseCommand();
        command.id = id;
        command.message = message;
        return command;
    }

}
