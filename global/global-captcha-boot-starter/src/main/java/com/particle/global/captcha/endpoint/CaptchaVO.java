package com.particle.global.captcha.endpoint;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class CaptchaVO extends VO {

	@Schema(description = "验证码唯一标识")
	private String captchaUniqueIdentifier;

	@Schema(description = "验证码base64字符串")
	private String base64;

	public static CaptchaVO create(String captchaUniqueIdentifier, String base64) {
		CaptchaVO captchaVO = new CaptchaVO();
		captchaVO.setCaptchaUniqueIdentifier(captchaUniqueIdentifier);
		captchaVO.setBase64(base64);
		return captchaVO;
	}
}
