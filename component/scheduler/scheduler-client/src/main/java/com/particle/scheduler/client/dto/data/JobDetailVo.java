package com.particle.scheduler.client.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
@Schema(description="任务响应数据对象")
public class JobDetailVo extends NameAndGroupVo {

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

    @Schema(description = "类名称")
    private String jobClassName;

    @Schema(description = "描述信息")
    private String description;

    @Schema(description = "如果没有关联触发器，是否持久化")
    private Boolean isDurable;

    @Schema(description = "执行完成是否持久化")
    private Boolean isPersistJobDataAfterExecution;

    @Schema(description = "是否不允许并行")
    private Boolean isConcurrentExectionDisallowed;

    @Schema(description = "是否可恢复")
    private Boolean isRecovery;

    @Schema(description = "任务元数据")
    private Map<String,Object> dataMap;

    @Schema(description = "cron表达式")
    private String cronExpression;

}
