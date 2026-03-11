package com.particle.common.client.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 通用 发布指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 10:36:54
 */
@Data
@Schema
public class CommonPublicCommand extends AbstractIdCommand {

    @NotNull(message = "是否发布 不能为空")
    @Schema(description = "是否发布",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isPublic;

    public static CommonPublicCommand create(Long id,Boolean isPublic) {
        CommonPublicCommand commonPublicCommand = new CommonPublicCommand();
        commonPublicCommand.setId(id);
        commonPublicCommand.setIsPublic(isPublic);
        return commonPublicCommand;
    }
}
