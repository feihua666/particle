package com.particle.func.client.application.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 功能应用 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Data
@Schema
public class FuncApplicationUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "应用编码 不能为空")
    @Schema(description = "应用编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "功能应用名称 不能为空")
    @Schema(description = "功能应用名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "是否禁用 不能为空")
    @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;


    @Schema(description = "禁用原因")
    private String disabledReason;


    @Schema(description = "应用主题")
    private String applicationTheme;


    @Schema(description = "应用默认的页面路由")
    private String applicationDefaultRoute;


    @Schema(description = "应用logo地址")
    private String applicationLogoUrl;

    @Schema(description = "应用图标地址")
    private String applicationIconUrl;

    @Schema(description = "额外配置json")
    private String configJson;

    @NotNull(message = "是否为分组 不能为空")
    @Schema(description = "是否为分组",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isGroup;

    @NotNull(message = "排序 不能为空")
    @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "父级")
    private Long parentId;

}
