package com.particle.global.captcha.endpoint;

import com.particle.global.dto.basic.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 验证码响应数据对象
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 14:25
 */
@Data
@ApiModel
public class CaptchaVO extends VO {

	@ApiModelProperty("验证码唯一标识")
	private String captchaUniqueIdentifier;

	@ApiModelProperty("验证码base64字符串")
	private String base64;

	public static CaptchaVO create(String captchaUniqueIdentifier, String base64) {
		CaptchaVO captchaVO = new CaptchaVO();
		captchaVO.setCaptchaUniqueIdentifier(captchaUniqueIdentifier);
		captchaVO.setBase64(base64);
		return captchaVO;
	}
}
