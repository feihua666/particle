package com.particle.scheduler.client.temptask.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 任务计划临时任务运行记录日志 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Data
@Schema
public class SchedulerTempTaskRunRecordLogCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "临时任务运行记录id 不能为空")
        @Schema(description = "临时任务运行记录id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long schedulerTempTaskRunRecordId;


    @NotEmpty(message = "日志级别 不能为空")
        @Schema(description = "日志级别",requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;


    @NotEmpty(message = "日志内容 不能为空")
        @Schema(description = "日志内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;









}
