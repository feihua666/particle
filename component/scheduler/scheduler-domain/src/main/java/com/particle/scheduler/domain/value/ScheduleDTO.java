package com.particle.scheduler.domain.value;

import com.particle.global.dto.basic.VO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 任务计划响应对象
 * Created by yangwei
 * Created at 2021/2/2 13:22
 */
@Setter
@Getter
public class ScheduleDTO extends VO {
    /**
     * 任务计划名称
     */
    private String schedulerName;
    /**
     * 任务计划实例id
     */
    private String schedulerInstanceId;

    /**
     * 任务计划上下文数据
     */
    private Map<String,Object> scheduleContextDataMap;

    /**
     * 是否开启
     */
    private Boolean isStarted;

    /**
     * 是否挂起
     */
    private Boolean isInStandbyMode;

    /**
     * 是否停止
     */
    private Boolean isShutdown;

    /**
     * 配置信息
     */
    private ScheduleMetaDataDTO scheduleMetaData;

    /**
     * 任务计划监听器
     */
    List<ScheduleListenerDTO> scheduleListeners;

    /**
     * 触发器监听器
     */
    List<TriggerListenerDTO> triggerListeners;

    /**
     * 任务监听器
     */
    List<JobListenerDTO> jobListeners;
}
