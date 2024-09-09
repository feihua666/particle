package com.particle.scheduler.domain.value;

import com.particle.common.domain.ValueObjRoot;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务计划查询表单对象
 * Created by yangwei
 * Created at 2021/2/2 13:25
 */
@Setter
@Getter
public class ScheduleQueryParam extends ValueObjRoot {

    /**
     * 任务计划名称
     */
    private String schedulerName;
    /**
     * 任务计划实例id
     */
    private String schedulerInstanceId;


    public static ScheduleQueryParam create(String schedulerName, String schedulerInstanceId) {
        ScheduleQueryParam scheduleQueryParam = new ScheduleQueryParam();
        scheduleQueryParam.schedulerName = schedulerName;
        scheduleQueryParam.schedulerInstanceId = schedulerInstanceId;
        return scheduleQueryParam;
    }
}
