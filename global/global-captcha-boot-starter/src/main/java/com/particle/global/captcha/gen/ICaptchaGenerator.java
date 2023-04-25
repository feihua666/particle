package com.particle.global.captcha.gen;

/**
 * <p>
 * 验证码生成器，主要是为了可自定义
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:27
 */
public interface ICaptchaGenerator {

	/**
	 * 验证码生成扩展生成器
	 * @param captchaGenDTO
	 * @return
	 */
	CaptchaGenResultDTO gen(CaptchaGenDTO captchaGenDTO);
}
