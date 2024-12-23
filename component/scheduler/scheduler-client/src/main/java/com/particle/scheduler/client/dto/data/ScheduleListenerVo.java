package com.particle.scheduler.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
@Schema(description="任务计划监听响应数据对象")
public class ScheduleListenerVo extends VO {

    @Schema(description = "监听类名称")
    private String className;
}
