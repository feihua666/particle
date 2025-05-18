package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2025-04-20 11:10:46
 */
@Setter
@Getter
@Schema(description = "aes解密表单对象")
public class AesDecryptCommand extends AbstractBaseCommand {

    @NotEmpty(message = "密文数据不能为空")
    @Schema(description = "密文数据base64编码")
    private String data;

    @NotEmpty(message = "解密密钥不能为空")
    @Schema(description = "解密密钥base64编码")
    private String key;
}
