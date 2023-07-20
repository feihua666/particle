package com.particle.func.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 功能组 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Data
@Schema
public class FuncGroupCreateCommand extends AbstractBaseCommand {

    @NotEmpty(message = "编码不能为空")
    @Schema(description = "编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @NotEmpty(message = "名称不能为空")
    @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "描述")
    private String remark;


}
