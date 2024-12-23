package com.particle.func.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
@Schema
public class FuncMoveCommand extends AbstractBaseUpdateCommand {

    @NotNull(message = "父级id不能为空")
    @Schema(description = "父级id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long parentId;
}
