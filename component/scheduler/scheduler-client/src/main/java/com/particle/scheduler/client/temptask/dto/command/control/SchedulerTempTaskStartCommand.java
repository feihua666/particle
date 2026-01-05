package com.particle.scheduler.client.temptask.dto.command.control;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 任务计划临时任务 开始指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-29 21:09:08
 */
@Data
@Schema
public class SchedulerTempTaskStartCommand extends AbstractBaseCommand {

    @NotEmpty(message = "临时任务编码 不能为空")
    @Schema(description = "临时任务编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "临时任务名称 不能为空")
    @Schema(description = "临时任务名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


}
