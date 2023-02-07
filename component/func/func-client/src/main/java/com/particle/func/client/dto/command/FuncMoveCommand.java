package com.particle.func.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 菜单功能 移动节点指令对象
 * </p>
 *
 * @author yw
 * @since 2023-02-06
 */
@PropValid
@Data
@ApiModel
public class FuncMoveCommand extends AbstractBaseUpdateCommand {

    @NotNull(message = "父级id不能为空")
    @ApiModelProperty(value = "父级id",required = true)
    private Long parentId;
}
