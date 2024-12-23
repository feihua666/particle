package com.particle.scheduler.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 17:25
 */
@Setter
@Getter
@Schema(description="NameAndGroup表单对象")
public class NameAndGroupCommand extends AbstractBaseCommand {

    @NotEmpty(message = "名称不能为空")
    @Schema(description = "名称",required = true)
    private String name;

    @NotEmpty(message = "组不能为空")
    @Schema(description = "组",required = true)
    private String group;


    public NameAndGroupCommand createCopy(){
        NameAndGroupCommand nameAndGroupCommand = new NameAndGroupCommand();
        nameAndGroupCommand.setName(name + "Copy");
        nameAndGroupCommand.setGroup(group + "Copy");
        return nameAndGroupCommand;
    }
}
