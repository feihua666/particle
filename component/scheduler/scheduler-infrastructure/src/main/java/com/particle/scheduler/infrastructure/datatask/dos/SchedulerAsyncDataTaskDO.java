package com.particle.scheduler.infrastructure.datatask.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划异步任务数据表
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Accessors(chain = true)
@Data
@TableName("component_scheduler_async_data_task")
public class SchedulerAsyncDataTaskDO extends BaseDO {

    /**
    * 任务分组标识，用于区分不同的任务数据
    */
    private String groupIdentifier;

    /**
    * 唯一标识，用于唯一区分不同的任务数据
    */
    private String uniqueIdentifier;

    /**
    * 执行参数
    */
    private String params;

    /**
    * 执行状态，字典id
    */
    private Long executeStatusDictId;

    /**
    * 执行错误时提示信息
    */
    private String errorMessage;

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

    /**
    * 数据过期时间，用于清理过期的无用数据
    */
    private LocalDateTime dataExpireAt;
    

}
