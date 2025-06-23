package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 添加包装出库和入库 指令
 * Created by yw
 * Created at 2025-06-20 17:59:34
 */
@Setter
@Getter
@Schema(description = "添加出库和入库表单对象")
public class AddWrapWarehouseAndWrapExWarehouseCommand extends AbstractBaseCommand {

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
     * 后端所有的需要添加字段的都是以领域模型为前缀的
     * 以该名称为前缀匹配
     * 注意首字母要大家，应该是类名称，不带后缀
     */
    // @NotEmpty(message = "模板领域模型名称 不能为空")
    // @Schema(description = "模板领域模型名称")
    // private String templateDomainName;
    /**
     * 例如：/Users/yw/fh/git-source/particle/component/tenant
     * 注意后面带组件名称
     */
    @NotEmpty(message = "模板后端组件绝对路径 不能为空")
    @Schema(description = "模板后端组件绝对路径")
    private String templateComponentBackendAbsolutePath;

}
