package com.particle.scheduler.domain.schedule;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划执行记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Data
@Entity
public class SchedulerExecuteRecord extends AggreateRoot {

    private SchedulerExecuteRecordId id;

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



    /**
     * 创建任务计划执行记录领域模型对象
     * @return 任务计划执行记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static SchedulerExecuteRecord create(){
        return DomainFactory.create(SchedulerExecuteRecord.class);
    }
}
