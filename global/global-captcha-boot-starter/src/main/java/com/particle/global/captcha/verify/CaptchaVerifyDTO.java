package com.particle.global.captcha.verify;

import com.particle.global.captcha.CaptchaBaseDTO;
import lombok.Data;

/**
 * <p>
 * 验证码验证传输对象
 * </p>
 *
 * @author yangwei
 * @since 2023-04-24 20:53
 */
@Data
public class CaptchaVerifyDTO extends CaptchaBaseDTO {


	/**
	 * 需要验证的值
	 */
	private String captchaValue;

	/**
	 * 在校验完成后是否清除验证码
	 * 如果在前端事先校验一般不清楚，只是增加用户体验
	 */
	private Boolean isRemove = true;


	public static CaptchaVerifyDTO create(String captchaUniqueIdentifier, String captchaValue) {
		CaptchaVerifyDTO captchaVerifyDTO = new CaptchaVerifyDTO();
		captchaVerifyDTO.fill(captchaUniqueIdentifier);
		captchaVerifyDTO.setCaptchaValue(captchaValue);
		return captchaVerifyDTO;
	}
}
