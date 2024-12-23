package com.particle.scheduler.client.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:49
 */
@Setter
@Getter
@Schema(description="触发器响应对象")
public class TriggerVo extends NameAndGroupVo {

    @Schema(description = "类名称")
    private String triggerClassName;

    @Schema(description = "触发器状态")
    private String triggerState;

    @Schema(description = "日历名称")
    private String calendarName;

    @Schema(description = "描述信息")
    private String description;

    @Schema(description = "优先级")
    private int priority;

    @Schema(description = "是否可以再次触发")
    private Boolean isMayFireAgain;

    @Schema(description = "开始于")
    private Date startAt;

    @Schema(description = "结束于")
    private Date endAt;

    @Schema(description = "下一次触发时间")
    private Date nextFireAt;

    @Schema(description = "上一次触发时间")
    private Date previousFireAt;

    @Schema(description = "最后触发时间")
    private Date finalFireAt;

    @Schema(description = "失火说明")
    private int misfireInstruction;

    @Schema(description = "cron表达式")
    private String cronExpression;

}
