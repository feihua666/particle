package com.particle.global.captcha;

/**
 * <p>
 * 自定义字符串场景实现
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 14:14
 */
public class CustomCaptchaScene implements ICaptchaScene{

	private String scene;

	@Override
	public String getScene() {
		return scene;
	}

	public static CustomCaptchaScene create(String scene) {
		CustomCaptchaScene customCaptchaScene = new CustomCaptchaScene();
		customCaptchaScene.scene = scene;
		return customCaptchaScene;
	}
}
