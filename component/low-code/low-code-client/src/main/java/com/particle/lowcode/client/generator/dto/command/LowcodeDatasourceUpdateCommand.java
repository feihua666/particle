package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 低代码数据源 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Data
@ApiModel
public class LowcodeDatasourceUpdateCommand extends AbstractBaseUpdateCommand {

    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "编码",required = true)
    private String code;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @NotEmpty(message = "驱动类名不能为空")
    @ApiModelProperty(value = "驱动类名",required = true)
    private String driverClassName;

    @NotEmpty(message = "地址不能为空")
    @ApiModelProperty(value = "地址",required = true)
    private String url;

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty("描述,注意事项等")
    private String remark;


}
