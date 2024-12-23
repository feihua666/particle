package com.particle.scheduler.infrastructure.temptask.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划临时任务运行记录表
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Accessors(chain = true)
@Data
@TableName("component_scheduler_temp_task_run_record")
public class SchedulerTempTaskRunRecordDO extends BaseDO {

    /**
    * 临时任务id
    */
    private Long schedulerTempTaskId;

    /**
    * 临时任务状态，字典id，如：执行中、执行完成
    */
    private Long executeStatusDictId;

    /**
    * 是否有异常，有异常=1，否则为0
    */
    private Boolean isHasError;

    /**
    * 是否允许运行开关，允许运行=1，停止运行=0
    */
    private Boolean isAllowRunSwitch;

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
	 * 日志追踪id
	 */
	private String traceId;

	/**
	 * 运行结果，运行成果物
	 */
	private String result;


}
