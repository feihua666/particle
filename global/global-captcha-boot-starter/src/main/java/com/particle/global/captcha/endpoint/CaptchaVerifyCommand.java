package com.particle.global.captcha.endpoint;

import com.particle.global.dto.basic.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 验证码校验命令
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 14:28
 */
@Data
@Schema
public class CaptchaVerifyCommand extends Command {

	public static String captchaUniqueIdentifierFieldName = "captchaUniqueIdentifier";
	public static String captchaValueFieldName = "captchaValue";


	@NotEmpty(message = "验证码唯一标识不能为空")
	@Schema(description = "验证码唯一标识")
	private String captchaUniqueIdentifier;

	@NotEmpty(message = "验证码不能为空")
	@Schema(description = "用户输入值")
	private String captchaValue;

}
