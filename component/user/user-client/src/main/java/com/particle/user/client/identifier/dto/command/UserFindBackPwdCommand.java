package com.particle.user.client.identifier.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户 修改密码 指令对象
 * </p>
 *
 * @author yw
 * @since 2022-12-25
 */
@Data
@Schema
public class UserFindBackPwdCommand extends UserIdentifierPwdCommand {

    @NotEmpty(message = "登录标识 不能为空")
    @Schema(description = "登录标识",required = true)
    private String identifier;


    /**
     * 该字段名和 {@link CaptchaVerifyCommand#captchaUniqueIdentifierFieldName}保持一致
     */
    @Schema(description = "验证码唯一标识")
    private String captchaUniqueIdentifier;

    /**
     * 该字段名和 {@link CaptchaVerifyCommand#captchaValueFieldName}保持一致
     */
    @Schema(description = "用户输入值,动态验证码登录时必填")
    private String captchaValue;
}
