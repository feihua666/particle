package com.particle.scheduler.domain.value;

import com.particle.global.dto.basic.VO;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务计划监听响应数据对象
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
public class ScheduleListenerDTO extends VO {

    /**
     * 监听类名称
     */
    private String className;
}
