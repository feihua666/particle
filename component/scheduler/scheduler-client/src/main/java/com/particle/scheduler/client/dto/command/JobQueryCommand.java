package com.particle.scheduler.client.dto.command;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:25
 */
@Setter
@Getter
@ApiModel(value="任务查询表单对象")
public class JobQueryCommand extends NameAndGroupQueryCommand {
}