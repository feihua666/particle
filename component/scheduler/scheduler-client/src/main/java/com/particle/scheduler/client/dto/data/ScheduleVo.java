package com.particle.scheduler.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:22
 */
@Setter
@Getter
@ApiModel(value="任务计划响应对象")
public class ScheduleVo extends VO {
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

    @ApiModelProperty(value = "任务计划上下文数据")
    private Map<String,Object> scheduleContextDataMap;

    @ApiModelProperty(value = "是否开启")
    private Boolean isStarted;

    @ApiModelProperty(value = "是否挂起")
    private Boolean isInStandbyMode;

    @ApiModelProperty(value = "是否停止")
    private Boolean isShutdown;

    @ApiModelProperty(value = "配置信息")
    private ScheduleMetaDataVo scheduleMetaData;

    @ApiModelProperty(value = "任务计划监听器")
    List<ScheduleListenerVo> scheduleListeners;

    @ApiModelProperty(value = "触发器监听器")
    List<TriggerListenerVo> triggerListeners;

    @ApiModelProperty(value = "任务监听器")
    List<JobListenerVo> jobListeners;
}
