package com.particle.scheduler.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:25
 */
@Setter
@Getter
@ApiModel(value="任务计划查询表单对象")
public class ScheduleQueryCommand extends AbstractBaseQueryCommand {

    /**
     * 任务计划名称
     */
    @ApiModelProperty(value = "任务计划名称")
    private String schedulerName;
    /**
     * 任务计划实例id
     */
    @ApiModelProperty(value = "任务计划实例id")
    private String schedulerInstanceId;
}
