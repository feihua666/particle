package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 导航分类 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Data
@Schema
public class NavigationCategoryCreateCommand extends AbstractBaseCommand {



    @Schema(description = "编码")
    private String code;


    @NotEmpty(message = "名称 不能为空")
        @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "图标")
    private String icon;


    @Schema(description = "描述")
    private String remark;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;



    @Schema(description = "父级")
    private Long parentId;



















}
