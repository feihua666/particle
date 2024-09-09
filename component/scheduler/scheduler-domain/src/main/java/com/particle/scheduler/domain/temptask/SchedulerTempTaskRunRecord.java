package com.particle.scheduler.domain.temptask;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划临时任务运行记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Data
@Entity
public class SchedulerTempTaskRunRecord extends AggreateRoot {

    private SchedulerTempTaskRunRecordId id;

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



    /**
     * 创建任务计划临时任务运行记录领域模型对象
     * @return 任务计划临时任务运行记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static SchedulerTempTaskRunRecord create(){
        return DomainFactory.create(SchedulerTempTaskRunRecord.class);
    }
}