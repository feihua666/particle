package com.particle.global.captcha;

/**
 * <p>
 * 验证码的生成或验证场景
 * </p>
 *
 * @author yangwei
 * @since 2023-04-24 18:26
 */
public interface ICaptchaScene {
	/**
	 * 场景值
	 * @return
	 */
	String scene();
}
