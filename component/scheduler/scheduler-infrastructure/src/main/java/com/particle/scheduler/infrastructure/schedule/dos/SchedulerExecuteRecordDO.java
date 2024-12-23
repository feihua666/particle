package com.particle.scheduler.infrastructure.schedule.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划执行记录表
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Accessors(chain = true)
@Data
@TableName("component_scheduler_execute_record")
public class SchedulerExecuteRecordDO extends BaseDO {

    /**
    * schedulerName
    */
    private String schedulerName;

    /**
    * schedulerInstanceId
    */
    private String schedulerInstanceId;

    /**
    * 任务名称
    */
    private String name;

    /**
    * 任务组
    */
    private String groupName;

    /**
    * 执行参数，json
    */
    private String params;

    /**
    * 执行状态，字典id
    */
    private Long executeStatusDictId;

    /**
    * 运行开始时间
    */
    private LocalDateTime startAt;

    /**
    * 运行结束时间
    */
    private LocalDateTime finishAt;

    /**
    * 本地主机ip，用来表明是在哪个机器上运行的
    */
    private String localHostIp;

    /**
    * 本地主机名称，用来表明是在哪个机器上运行的
    */
    private String localHostName;

    /**
    * 链路追踪id
    */
    private String traceId;

    /**
    * 运行结果，运行成果物
    */
    private String result;


}
