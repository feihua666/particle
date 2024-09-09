package com.particle.scheduler.domain.temptask;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 任务计划临时任务 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Data
@Entity
public class SchedulerTempTask extends AggreateRoot {

    private SchedulerTempTaskId id;

    /**
    * 临时任务编码
    */
    private String code;

    /**
    * 临时任务名称
    */
    private String name;



    /**
     * 创建任务计划临时任务领域模型对象
     * @return 任务计划临时任务领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static SchedulerTempTask create(){
        return DomainFactory.create(SchedulerTempTask.class);
    }
}
