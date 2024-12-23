package com.particle.scheduler.client.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/4 13:00
 */
@Setter
@Getter
@Schema(description="任务计划停止表单对象")
public class ScheduleShutdownCommand extends ScheduleCommand {

    @Schema(description = "是否等待任务完成")
    private Boolean isWaitForJobsToComplete;
}
