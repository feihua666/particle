package com.particle.scheduler.client.temptask.dto.command.control;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskControlApplicationService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 任务计划临时任务 结束指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-29 21:09:08
 */
@Data
@Schema
public class SchedulerTempTaskFinishCommand extends AbstractBaseCommand {

    @NotNull(message = "任务运行记录id 不能为空")
    @Schema(description = "任务运行记录id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    /**
     * 如果有异常，异常的内容应该使用 {@link ISchedulerTempTaskControlApplicationService#log(String, Long, String)}记录
     */
    @NotNull(message = "是否发生错误 不能为空")
    @Schema(description = "是否发生错误",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isHasError;

    @NotEmpty(message = "任务运行结果 不能为空")
    @Schema(description = "任务运行结果",requiredMode = Schema.RequiredMode.REQUIRED)
    private String result;

}
