package com.particle.global.captcha.endpoint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 动态验证码生成命令
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 14:28
 */
@Data
@ApiModel
public class DynamicCaptchaGenCommand extends CaptchaGenCommand {

	@NotEmpty(message = "手机号或邮箱不能为空")
	@ApiModelProperty(value = "手机号或邮箱",required = true)
	private String identifier;

	/**
	 * 通知标识，可以传一个消息模板，或其它标识以触发通知时能够识别到对应的具体消息通知内容
	 * 可以通过后端配置，根据场景值配置对应的通知标识，如果配置存在是该值将忽略
	 * 参见 {@link DynamicCaptchaNotifyProperties} 配置
	 */
	@ApiModelProperty(value = "通知标识")
	private String notifyIdentifier;
}
