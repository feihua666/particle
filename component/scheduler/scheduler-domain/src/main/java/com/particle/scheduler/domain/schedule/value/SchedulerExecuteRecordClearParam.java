package com.particle.scheduler.domain.schedule.value;

import com.particle.common.domain.ValueObjRoot;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划执行记录清理表单对象
 * </p>
 *
 * @author yw
 * @since 2021-10-11
 */
@Setter
@Getter
public class SchedulerExecuteRecordClearParam extends ValueObjRoot {

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
    private String group;

    /**
     * 该时间以前的数据将被清理
     */
    @NotNull(message = "时间不能为空")
    private LocalDateTime beforeAt;

}
