package com.particle.scheduler.client.temptask.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 任务计划临时任务运行记录日志 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Data
@Schema
public class SchedulerTempTaskRunRecordLogUpdateCommand extends AbstractBaseUpdateCommand {



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
