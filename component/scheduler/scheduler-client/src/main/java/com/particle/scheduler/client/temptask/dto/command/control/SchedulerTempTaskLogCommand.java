package com.particle.scheduler.client.temptask.dto.command.control;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class SchedulerTempTaskLogCommand extends SchedulerTempTaskLogBaseCommand {

    @NotNull(message = "日志级别 不能为空")
    @Schema(description = "日志级别，如：info、error",requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;

    public static SchedulerTempTaskLogCommand create(Long id, String level, String message){
        // 设计属性
        SchedulerTempTaskLogCommand schedulerTempTaskLogCommand = new SchedulerTempTaskLogCommand();
        schedulerTempTaskLogCommand.id = id;
        schedulerTempTaskLogCommand.level = level;
        schedulerTempTaskLogCommand.message = message;

        return schedulerTempTaskLogCommand;

    }

}
