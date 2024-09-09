package com.particle.scheduler.domain.value;

import com.particle.global.dto.basic.VO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 任务计划元数据响应数据对象
 * 转为vo {@link org.quartz.SchedulerMetaData}
 * Created by yangwei
 * Created at 2021/2/2 14:19
 */
@Setter
@Getter
public class ScheduleMetaDataDTO extends VO {


    /**
     * 任务计划名称
     */
    private String schedulerName;

    /**
     * 任务计划实例id
     */
    private String schedulerInstanceId;

    /**
     * 任务计划实例类
     */
    private String schedulerClassName;

    /**
     * 是否远程
     */
    private Boolean isSchedulerRemote;

    /**
     * 是否启动
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
     * 开始时间
     */
    private Date startAt;

    /**
     * 已执行任务数，从启动开始
     */
    private int numberOfJobsExecuted;

    /**
     * 任务存储类
     */
    private String jobStoreClassName;

    /**
     * 是否任务存储支持持久化
     */
    private Boolean isJobStoreSupportsPersistence;

    /**
     * 是否集群模式
     */
    private Boolean isJobStoreClustered;

    /**
     * 线程池类
     */
    private String threadPoolClassName;

    /**
     * 当前线程池线程数量
     */
    private int threadPoolSize;

    /**
     * quartz版本
     */
    private String version;
}
