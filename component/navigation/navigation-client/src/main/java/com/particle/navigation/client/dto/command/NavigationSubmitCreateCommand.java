package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 导航提交 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Data
@Schema
public class NavigationSubmitCreateCommand extends AbstractBaseCommand {

    @NotEmpty(message = "网站名称 不能为空")
    @Schema(description = "网站名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "网站标题 不能为空")
    @Schema(description = "网站标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotEmpty(message = "网站地址 不能为空")
    @Schema(description = "网站地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;

    @Schema(description = "描述")
    private String remark;

}
