package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * Created by yangwei
 * Created at 2021/2/5 17:48
 */
@Setter
@Getter
@ApiModel(value="cron执行查询表单对象")
public class CronQueryCommand extends AbstractBaseCommand {

    @ApiModelProperty("开始时间，不填写默认按服务器当前时间")
    private LocalDateTime startAt;

    @NotEmpty(message = "cron表达式不能为空")
    @ApiModelProperty("cron表达式")
    private String cronExpression;

    @Min(5)
    @Max(100)
    @ApiModelProperty("执行次数")
    private Integer times = 10;
}
