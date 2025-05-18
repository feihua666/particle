package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 添加 开放接口供应商 指令
 * Created by yw
 * Created at 2025-04-27 11:33:27
 */
@Setter
@Getter
@Schema(description = "添加出库和入库表单对象")
public class AddOpenapiExecuteProviderCommand extends AbstractBaseCommand {

    /**
     * 后端所有的需要添加字段的都是以领域模型为前缀的
     * 以该名称为前缀匹配
     * 注意首字母要大家，应该是类名称，不带后缀
     */
    @NotEmpty(message = "领域模型名称 不能为空")
    @Schema(description = "领域模型名称")
    private String domainName;

    /**
     * 例如：/Users/yw/fh/git-source/particle/component/tenant
     * 注意后面带组件名称
     */
    @NotEmpty(message = "后端组件绝对路径 不能为空")
    @Schema(description = "后端组件绝对路径")
    private String componentBackendAbsolutePath;

    /**
     * 例如：/Users/yw/fh/git-source/particle/component/tenant
     * 注意后面带组件名称
     */
    @NotEmpty(message = "模板后端组件绝对路径 不能为空")
    @Schema(description = "模板后端组件绝对路径")
    private String templateComponentBackendAbsolutePath;

    @NotNull(message = "是否单条 不能为空")
    @Schema(description = "是否单条，不是单条就是分页")
    private Boolean isSingle;

}
