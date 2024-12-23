package com.particle.scheduler.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 17:25
 */
@Setter
@Getter
@Schema(description="NameAndGroupQuery表单对象")
public class NameAndGroupQueryCommand extends AbstractBaseCommand {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "组")
    private String group;
}
