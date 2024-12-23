package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 导航网站标签关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Data
@Schema
public class NavigationSiteTagRelCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "网站id 不能为空")
        @Schema(description = "网站id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long navigationSiteId;


    @NotNull(message = "网站标签id 不能为空")
        @Schema(description = "网站标签id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long navigationSiteTagId;









}
