package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 内容 发布指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Data
@Schema
public class CmsContentPublicCommand extends AbstractBaseUpdateCommand {

    @NotNull(message = "是否发布 不能为空")
    @Schema(description = "是否发布",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isPublic;

}
