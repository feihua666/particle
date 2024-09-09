package com.particle.scheduler.domain.value;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 触发器响应对象
 * Created by yangwei
 * Created at 2021/2/2 13:49
 */
@Setter
@Getter
public class TriggerDTO extends NameAndGroupDTO {
    /**
     * 任务计划名称
     */
    private String schedulerName;
    /**
     * 任务计划实例id
     */
    private String schedulerInstanceId;

    /**
     * 类名称
     */
    private String triggerClassName;

    /**
     * 触发器状态
     */
    private String triggerState;

    /**
     * 日历名称
     */
    private String calendarName;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 优先级
     */
    private int priority;

    /**
     * 是否可以再次触发
     */
    private Boolean isMayFireAgain;

    /**
     * 开始于
     */
    private Date startAt;

    /**
     * 结束于
     */
    private Date endAt;

    /**
     * 下一次触发时间
     */
    private Date nextFireAt;

    /**
     * 上一次触发时间
     */
    private Date previousFireAt;

    /**
     * 最后触发时间
     */
    private Date finalFireAt;

    /**
     * 失火说明
     */
    private int misfireInstruction;

    /**
     * cron表达式
     */
    private String cronExpression;

}
