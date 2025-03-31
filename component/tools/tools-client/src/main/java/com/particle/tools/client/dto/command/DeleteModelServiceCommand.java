package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2025-01-17 15:55:13
 */
@Setter
@Getter
@Schema(description = "删除模型服务表单对象")
public class DeleteModelServiceCommand extends AbstractBaseCommand {
    /**
     * 例如：/Users/yw/fh/git-source/particle/component/tenant
     * 注意后面带组件名称
     */
    @NotEmpty(message = "后端组件绝对路径 不能为空")
    @Schema(description = "前端组件绝对路径")
    private String componentBackendAbsolutePath;

    /**
     * 例如：/Users/yw/fh/git-source/particle/web/component/pc/tenant
     * 注意后面带组件名称
     */
    @NotEmpty(message = "前端组件绝对路径 不能为空")
    @Schema(description = "前端组件绝对路径")
    private String componentFrontendAbsolutePath;

    /**
     * 后端所有的需要添加字段的都是以领域模型为前缀的
     * 以该名称为前缀匹配
     * 注意首字母要大家，应该是类名称，不带后缀
     */
    @NotEmpty(message = "领域模型名称 不能为空")
    @Schema(description = "领域模型名称")
    private String domainName;

}
