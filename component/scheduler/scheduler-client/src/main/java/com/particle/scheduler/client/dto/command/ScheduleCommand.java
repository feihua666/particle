package com.particle.scheduler.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:25
 */
@Setter
@Getter
@Schema(description="任务计划查询表单对象")
public class ScheduleCommand extends AbstractBaseCommand {


    @NotEmpty(message = "任务计划名称不能为空")
    @Schema(description = "任务计划名称")
    private String schedulerName;

    @NotEmpty(message = "任务计划实例id不能为空")
    @Schema(description = "任务计划实例id")
    private String schedulerInstanceId;
}
