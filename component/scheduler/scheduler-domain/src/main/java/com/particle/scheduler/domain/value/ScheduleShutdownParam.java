package com.particle.scheduler.domain.value;

import lombok.Getter;
import lombok.Setter;

/**
 * 任务计划停止表单对象
 * Created by yangwei
 * Created at 2021/2/4 13:00
 */
@Setter
@Getter
public class ScheduleShutdownParam extends ScheduleParam {

    /**
     * 是否等待任务完成
     */
    private Boolean isWaitForJobsToComplete;
}
