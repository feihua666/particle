package com.particle.scheduler.client.datatask.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划异步任务数据 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Data
@Schema
public class SchedulerAsyncDataTaskUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "任务分组标识 不能为空")
        @Schema(description = "任务分组标识",requiredMode = Schema.RequiredMode.REQUIRED)
    private String groupIdentifier;


    @Schema(description = "唯一标识")
    private String uniqueIdentifier;


    @Schema(description = "执行参数")
    private String params;


    @NotNull(message = "执行状态 不能为空")
        @Schema(description = "执行状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long executeStatusDictId;


    @Schema(description = "执行错误时提示信息")
    private String errorMessage;


        @Schema(description = "运行开始时间")
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


    @NotNull(message = "数据过期时间 不能为空")
        @Schema(description = "数据过期时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime dataExpireAt;









}
