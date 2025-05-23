package com.particle.scheduler.domain.datatask;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划任务数据 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Data
@Entity
public class SchedulerJobDataTask extends AggreateRoot {

    private SchedulerJobDataTaskId id;

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
    


    /**
     * 创建任务计划任务数据领域模型对象
     * @return 任务计划任务数据领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static SchedulerJobDataTask create(){
        return DomainFactory.create(SchedulerJobDataTask.class);
    }
}
