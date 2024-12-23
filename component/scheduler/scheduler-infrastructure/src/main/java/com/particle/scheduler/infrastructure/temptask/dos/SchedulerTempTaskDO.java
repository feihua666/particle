package com.particle.scheduler.infrastructure.temptask.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 任务计划临时任务表
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Accessors(chain = true)
@Data
@TableName("component_scheduler_temp_task")
public class SchedulerTempTaskDO extends BaseDO {

    /**
    * 临时任务编码
    */
    private String code;

    /**
    * 临时任务名称
    */
    private String name;


}
