package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 导航网站分类关系 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Data
@Schema
public class NavigationSiteCategoryRelUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "导航网站id 不能为空")
        @Schema(description = "导航网站id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long navigationSiteId;


    @NotNull(message = "导航分类id 不能为空")
        @Schema(description = "导航分类id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long navigationCategoryId;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;









}
