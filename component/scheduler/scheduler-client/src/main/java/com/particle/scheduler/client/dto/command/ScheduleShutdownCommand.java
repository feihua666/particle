package com.particle.scheduler.client.dto.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/4 13:00
 */
@Setter
@Getter
@ApiModel(value="任务计划停止表单对象")
public class ScheduleShutdownCommand extends ScheduleCommand {

    @ApiModelProperty("是否等待任务完成")
    private Boolean isWaitForJobsToComplete;
}
