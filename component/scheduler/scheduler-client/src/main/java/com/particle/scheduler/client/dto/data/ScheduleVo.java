package com.particle.scheduler.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description="任务计划响应对象")
public class ScheduleVo extends VO {
    /**
     * 任务计划名称
     */
    @Schema(description = "任务计划名称")
    private String schedulerName;
    /**
     * 任务计划实例id
     */
    @Schema(description = "任务计划实例id")
    private String schedulerInstanceId;

    @Schema(description = "任务计划上下文数据")
    private Map<String,Object> scheduleContextDataMap;

    @Schema(description = "是否开启")
    private Boolean isStarted;

    @Schema(description = "是否挂起")
    private Boolean isInStandbyMode;

    @Schema(description = "是否停止")
    private Boolean isShutdown;

    @Schema(description = "配置信息")
    private ScheduleMetaDataVo scheduleMetaData;

    @Schema(description = "任务计划监听器")
    List<ScheduleListenerVo> scheduleListeners;

    @Schema(description = "触发器监听器")
    List<TriggerListenerVo> triggerListeners;

    @Schema(description = "任务监听器")
    List<JobListenerVo> jobListeners;
}
