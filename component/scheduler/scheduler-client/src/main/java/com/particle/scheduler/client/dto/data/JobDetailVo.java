package com.particle.scheduler.client.dto.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
@ApiModel(value="任务响应数据对象")
public class JobDetailVo extends NameAndGroupVo {

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

    @ApiModelProperty(value = "类名称")
    private String jobClassName;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "如果没有关联触发器，是否持久化")
    private Boolean isDurable;

    @ApiModelProperty(value = "执行完成是否持久化")
    private Boolean isPersistJobDataAfterExecution;

    @ApiModelProperty(value = "是否不允许并行")
    private Boolean isConcurrentExectionDisallowed;

    @ApiModelProperty(value = "是否可恢复")
    private Boolean isRecovery;

    @ApiModelProperty(value = "任务元数据")
    private Map<String,Object> dataMap;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

}