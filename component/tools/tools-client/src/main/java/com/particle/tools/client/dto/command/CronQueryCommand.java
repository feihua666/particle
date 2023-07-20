package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "cron执行查询表单对象")
public class CronQueryCommand extends AbstractBaseCommand {

    @Schema(description = "开始时间，不填写默认按服务器当前时间")
    private LocalDateTime startAt;

    @NotEmpty(message = "cron表达式不能为空")
    @Schema(description = "cron表达式")
    private String cronExpression;

    @Min(5)
    @Max(100)
    @Schema(description = "执行次数")
    private Integer times = 10;
}
