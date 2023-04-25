package com.particle.global.captcha.endpoint;

import com.particle.global.dto.basic.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 验证码校验命令
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 14:28
 */
@Data
@ApiModel
public class CaptchaVerifyCommand extends Command {

	public static String captchaUniqueIdentifierFieldName = "captchaUniqueIdentifier";
	public static String captchaValueFieldName = "captchaValue";


	@NotEmpty(message = "验证码唯一标识不能为空")
	@ApiModelProperty("验证码唯一标识")
	private String captchaUniqueIdentifier;

	@NotEmpty(message = "验证码不能为空")
	@ApiModelProperty("用户输入值")
	private String captchaValue;

}
