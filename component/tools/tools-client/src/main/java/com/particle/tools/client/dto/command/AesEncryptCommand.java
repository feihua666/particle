package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created by yangwei
 * Created at 2021/2/5 17:48
 */
@Setter
@Getter
@ApiModel(value="aes加密表单对象")
public class AesEncryptCommand extends AbstractBaseCommand {


    @NotEmpty(message = "加密数据不能为空")
    @ApiModelProperty("加密数据")
    private String data;

    @NotEmpty(message = "加密密钥不能为空")
    @ApiModelProperty("加密密钥")
    private String key;
}
