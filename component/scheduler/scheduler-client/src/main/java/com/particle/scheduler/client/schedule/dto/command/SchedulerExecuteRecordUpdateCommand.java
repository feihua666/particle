package com.particle.scheduler.client.schedule.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划执行记录 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Data
@Schema
public class SchedulerExecuteRecordUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "schedulerName 不能为空")
        @Schema(description = "schedulerName",requiredMode = Schema.RequiredMode.REQUIRED)
    private String schedulerName;


    @NotEmpty(message = "schedulerInstanceId 不能为空")
        @Schema(description = "schedulerInstanceId",requiredMode = Schema.RequiredMode.REQUIRED)
    private String schedulerInstanceId;


    @NotEmpty(message = "任务名称 不能为空")
        @Schema(description = "任务名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "任务组 不能为空")
        @Schema(description = "任务组",requiredMode = Schema.RequiredMode.REQUIRED)
    private String groupName;


    @Schema(description = "执行参数")
    private String params;


    @NotEmpty(message = "执行状态 不能为空")
        @Schema(description = "执行状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long executeStatusDictId;


    @NotNull(message = "运行开始时间 不能为空")
        @Schema(description = "运行开始时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime startAt;


    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;


    @Schema(description = "本地主机ip")
    private String localHostIp;


    @Schema(description = "本地主机名称")
    private String localHostName;


    @Schema(description = "链路追踪id")
    private String traceId;


    @Schema(description = "运行结果")
    private String result;









}
