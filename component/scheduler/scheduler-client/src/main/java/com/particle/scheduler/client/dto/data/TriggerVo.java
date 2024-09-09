package com.particle.scheduler.client.dto.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:49
 */
@Setter
@Getter
@ApiModel(value="触发器响应对象")
public class TriggerVo extends NameAndGroupVo {

    @ApiModelProperty(value = "类名称")
    private String triggerClassName;

    @ApiModelProperty(value = "触发器状态")
    private String triggerState;

    @ApiModelProperty(value = "日历名称")
    private String calendarName;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "优先级")
    private int priority;

    @ApiModelProperty(value = "是否可以再次触发")
    private Boolean isMayFireAgain;

    @ApiModelProperty(value = "开始于")
    private Date startAt;

    @ApiModelProperty(value = "结束于")
    private Date endAt;

    @ApiModelProperty(value = "下一次触发时间")
    private Date nextFireAt;

    @ApiModelProperty(value = "上一次触发时间")
    private Date previousFireAt;

    @ApiModelProperty(value = "最后触发时间")
    private Date finalFireAt;

    @ApiModelProperty(value = "失火说明")
    private int misfireInstruction;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

}
