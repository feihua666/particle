package com.particle.scheduler.client.temptask.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划临时任务运行记录 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Data
@Schema
public class SchedulerTempTaskRunRecordUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "临时任务id 不能为空")
        @Schema(description = "临时任务id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long schedulerTempTaskId;


    @NotNull(message = "临时任务状态 不能为空")
        @Schema(description = "临时任务状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long executeStatusDictId;


    @NotNull(message = "是否有异常 不能为空")
        @Schema(description = "是否有异常",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isHasError;


    @NotNull(message = "是否允许运行开关 不能为空")
        @Schema(description = "是否允许运行开关",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isAllowRunSwitch;


    @NotNull(message = "运行开始时间 不能为空")
        @Schema(description = "运行开始时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime startAt;
    

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;
    

    @Schema(description = "本地主机ip")
    private String localHostIp;


    @Schema(description = "本地主机名称")
    private String localHostName;

	@Schema(description = "日志追踪id")
	private String traceId;

	@Schema(description = "运行结果，运行成果物")
	private String result;









}