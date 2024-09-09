package com.particle.scheduler.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="任务计划元数据响应数据对象")
public class ScheduleMetaDataVo extends VO {


    @ApiModelProperty(value = "任务计划名称")
    private String schedulerName;

    @ApiModelProperty(value = "任务计划实例id")
    private String schedulerInstanceId;

    @ApiModelProperty(value = "任务计划实例类")
    private String schedulerClassName;

    @ApiModelProperty(value = "是否远程")
    private Boolean isSchedulerRemote;

    @ApiModelProperty(value = "是否启动")
    private Boolean isStarted;

    @ApiModelProperty(value = "是否挂起")
    private Boolean isInStandbyMode;

    @ApiModelProperty(value = "是否停止")
    private Boolean isShutdown;

    @ApiModelProperty(value = "开始时间")
    private Date startAt;

    @ApiModelProperty(value = "已执行任务数，从启动开始")
    private int numberOfJobsExecuted;

    @ApiModelProperty(value = "任务存储类")
    private String jobStoreClassName;

    @ApiModelProperty(value = "是否任务存储支持持久化")
    private Boolean isJobStoreSupportsPersistence;

    @ApiModelProperty(value = "是否集群模式")
    private Boolean isJobStoreClustered;

    @ApiModelProperty(value = "线程池类")
    private String threadPoolClassName;

    @ApiModelProperty(value = "当前线程池线程数量")
    private int threadPoolSize;

    @ApiModelProperty(value = "quartz版本")
    private String version;
}
