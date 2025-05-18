package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/5 17:48
 */
@Setter
@Getter
@Schema(description = "aes加密表单对象")
public class AesEncryptCommand extends AbstractBaseCommand {


    @NotEmpty(message = "明文数据不能为空")
    @Schema(description = "明文数据")
    private String data;

    @NotEmpty(message = "加密密钥不能为空")
    @Schema(description = "加密密钥base64编码")
    private String key;
}
