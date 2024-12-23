package com.particle.scheduler.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 转为vo {@link org.quartz.SchedulerMetaData}
 * Created by yangwei
 * Created at 2021/2/2 14:19
 */
@Setter
@Getter
@Schema(description="任务计划元数据响应数据对象")
public class ScheduleMetaDataVo extends VO {


    @Schema(description = "任务计划名称")
    private String schedulerName;

    @Schema(description = "任务计划实例id")
    private String schedulerInstanceId;

    @Schema(description = "任务计划实例类")
    private String schedulerClassName;

    @Schema(description = "是否远程")
    private Boolean isSchedulerRemote;

    @Schema(description = "是否启动")
    private Boolean isStarted;

    @Schema(description = "是否挂起")
    private Boolean isInStandbyMode;

    @Schema(description = "是否停止")
    private Boolean isShutdown;

    @Schema(description = "开始时间")
    private Date startAt;

    @Schema(description = "已执行任务数，从启动开始")
    private int numberOfJobsExecuted;

    @Schema(description = "任务存储类")
    private String jobStoreClassName;

    @Schema(description = "是否任务存储支持持久化")
    private Boolean isJobStoreSupportsPersistence;

    @Schema(description = "是否集群模式")
    private Boolean isJobStoreClustered;

    @Schema(description = "线程池类")
    private String threadPoolClassName;

    @Schema(description = "当前线程池线程数量")
    private int threadPoolSize;

    @Schema(description = "quartz版本")
    private String version;
}
