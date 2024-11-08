package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 导航网站标签 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Data
@Schema
public class NavigationSiteTagCreateCommand extends AbstractBaseCommand {



    @Schema(description = "标签编码")
    private String code;


    @NotEmpty(message = "标签名称 不能为空")
        @Schema(description = "标签名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "分组 不能为空")
        @Schema(description = "分组",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long groupDictId;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;


    @Schema(description = "备注")
    private String remark;









}
