package com.particle.scheduler.domain.temptask;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 任务计划临时任务运行记录日志 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Data
@Entity
public class SchedulerTempTaskRunRecordLog extends AggreateRoot {

    private SchedulerTempTaskRunRecordLogId id;

    /**
    * 临时任务运行记录id
    */
    private Long schedulerTempTaskRunRecordId;

    /**
    * 日志级别，建议遵循java日志级别，info、warn、debug等
    */
    private String level;

    /**
    * 日志内容
    */
    private String content;



    /**
     * 创建任务计划临时任务运行记录日志领域模型对象
     * @return 任务计划临时任务运行记录日志领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static SchedulerTempTaskRunRecordLog create(){
        return DomainFactory.create(SchedulerTempTaskRunRecordLog.class);
    }
}
