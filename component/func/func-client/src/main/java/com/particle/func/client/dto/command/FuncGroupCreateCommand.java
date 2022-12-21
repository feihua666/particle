package com.particle.func.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class FuncGroupCreateCommand extends AbstractBaseCommand {

    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "编码",required = true)
    private String code;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty("描述")
    private String remark;


}
