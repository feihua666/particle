package com.particle.area.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 区域 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-27 11:22:42
 */
@Data
@Schema
public class AreaItemsQueryCommand extends AbstractBaseQueryCommand {


    @NotNull(message = "父级id不能为空")
    @Schema(description = "父级id")
    private Long parentId;
}
