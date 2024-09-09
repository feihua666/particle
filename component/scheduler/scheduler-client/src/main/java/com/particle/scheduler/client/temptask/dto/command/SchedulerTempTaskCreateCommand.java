package com.particle.scheduler.client.temptask.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 任务计划临时任务 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Data
@Schema
public class SchedulerTempTaskCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "临时任务编码 不能为空")
        @Schema(description = "临时任务编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "临时任务名称 不能为空")
        @Schema(description = "临时任务名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;









}
