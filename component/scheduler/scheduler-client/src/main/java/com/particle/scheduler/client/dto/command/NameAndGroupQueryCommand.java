package com.particle.scheduler.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 17:25
 */
@Setter
@Getter
@ApiModel(value="NameAndGroupQuery表单对象")
public class NameAndGroupQueryCommand extends AbstractBaseCommand {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "组")
    private String group;
}
