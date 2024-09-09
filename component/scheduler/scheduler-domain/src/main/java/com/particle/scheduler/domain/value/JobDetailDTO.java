package com.particle.scheduler.domain.value;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 任务响应数据对象
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
public class JobDetailDTO extends NameAndGroupDTO {

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
    private String jobClassName;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 如果没有关联触发器，是否持久化
     */
    private Boolean isDurable;

    /**
     * 执行完成是否持久化
     */
    private Boolean isPersistJobDataAfterExecution;

    /**
     * 是否不允许并行
     */
    private Boolean isConcurrentExectionDisallowed;

    /**
     * 是否可恢复
     */
    private Boolean isRecovery;

    /**
     * 任务元数据
     */
    private Map<String,Object> dataMap;

    /**
     * cron表达式
     */
    private String cronExpression;

}
