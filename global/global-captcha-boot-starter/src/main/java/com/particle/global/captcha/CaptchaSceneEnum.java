package com.particle.global.captcha;

/**
 * <p>
 * 一般常用的场景值枚举
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 09:42
 */
public enum CaptchaSceneEnum implements ICaptchaScene{
	/**
	 * 接口调用场景
	 */
	api,
	/**
	 * 手机app端后台管理登录
	 */
	mobile_admin_login,
	/**
	 * 手机app端前台登录
	 */
	mobile_front_login,
	/**
	 * 移动网页端后台管理登录
	 */
	wap_admin_login,
	/**
	 * 移动网页端前台登录
	 */
	wap_front_login,

	/**
	 * pc或平板网页端后台管理登录
	 */
	web_admin_login,
	/**
	 * pc或平板网页端前台登录
	 */
	web_front_login;

	@Override
	public String scene() {
		return this.name();
	}

}
