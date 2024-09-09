package com.particle.scheduler.infrastructure.temptask.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 任务计划临时任务运行记录日志表
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Accessors(chain = true)
@Data
@TableName("component_scheduler_temp_task_run_record_log")
public class SchedulerTempTaskRunRecordLogDO extends BaseDO {

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


}
