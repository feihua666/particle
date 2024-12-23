package com.particle.scheduler.client.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:25
 */
@Setter
@Getter
@Schema(description="任务查询表单对象")
public class JobQueryCommand extends NameAndGroupQueryCommand {
}
