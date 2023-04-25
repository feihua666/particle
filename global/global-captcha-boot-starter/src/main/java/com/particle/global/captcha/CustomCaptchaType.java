package com.particle.global.captcha;

/**
 * <p>
 * 自定义字符串场景实现
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 14:14
 */
public class CustomCaptchaType implements ICaptchaType{

	private String type;

	public static CustomCaptchaType create(String type) {
		CustomCaptchaType customCaptchaType = new CustomCaptchaType();
		customCaptchaType.type = type;
		return customCaptchaType;
	}

	@Override
	public String type() {
		return type;
	}
}
