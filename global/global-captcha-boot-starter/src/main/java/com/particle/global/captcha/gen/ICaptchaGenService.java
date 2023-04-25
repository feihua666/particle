package com.particle.global.captcha.gen;

/**
 * <p>
 * 验证码生成服务
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 09:35
 */
public interface ICaptchaGenService {


	/**
	 * 生成验证码
	 * @param captchaGenDTO
	 * @return
	 */
	CaptchaGenResultDTO gen(CaptchaGenDTO captchaGenDTO);
}
