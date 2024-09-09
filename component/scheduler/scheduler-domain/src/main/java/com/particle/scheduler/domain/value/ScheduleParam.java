package com.particle.scheduler.domain.value;

import com.particle.common.domain.ValueObjRoot;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 任务计划查询表单对象
 * Created by yangwei
 * Created at 2021/2/2 13:25
 */
@Setter
@Getter
public class ScheduleParam extends ValueObjRoot {


    /**
     * 任务计划名称
     */
    @NotEmpty(message = "任务计划名称不能为空")
    private String schedulerName;

    /**
     * 任务计划实例id
     */
    @NotEmpty(message = "任务计划实例id不能为空")
    private String schedulerInstanceId;

    public static ScheduleParam create(String schedulerName,String schedulerInstanceId) {
        ScheduleParam scheduleParam = new ScheduleParam();
        scheduleParam.schedulerName = schedulerName;
        scheduleParam.schedulerInstanceId = schedulerInstanceId;
        return scheduleParam;
    }
}
