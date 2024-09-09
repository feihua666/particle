package com.particle.scheduler.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:25
 */
@Setter
@Getter
@ApiModel(value="任务计划查询表单对象")
public class ScheduleCommand extends AbstractBaseCommand {


    @NotEmpty(message = "任务计划名称不能为空")
    @ApiModelProperty(value = "任务计划名称")
    private String schedulerName;

    @NotEmpty(message = "任务计划实例id不能为空")
    @ApiModelProperty(value = "任务计划实例id")
    private String schedulerInstanceId;
}
